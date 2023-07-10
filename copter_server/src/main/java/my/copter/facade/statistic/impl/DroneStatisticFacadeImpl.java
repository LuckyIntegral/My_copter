package my.copter.facade.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.data.dto.statistic.EventHistoryDto;
import my.copter.facade.statistic.DroneStatisticFacade;
import my.copter.persistence.elasticsearch.document.DroneSearchIndex;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
@AllArgsConstructor
public class DroneStatisticFacadeImpl implements DroneStatisticFacade {

    private final DronePDPSearchService service;
    private final CopterCrudService crudService;

    @Override
    public List<EventHistoryDto> findAllStatistic() {
        return service.findAll()
                .stream()
                .map(e -> {
                    EventHistoryDto dto = new EventHistoryDto();
                    dto.setDate(e.getCreated());
                    dto.setEventId(e.getDroneId());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<EventActivityDto> findAllPdpStatistic() {
        return service.findAll()
                .stream()
                .collect(Collectors.groupingBy(DroneSearchIndex::getDroneId, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    EventActivityDto dto = new EventActivityDto();
                    dto.setEvent(crudService.findById(e.getKey()).getName());
                    dto.setActivity(e.getValue());
                    return dto;
                }).toList();
    }
}
