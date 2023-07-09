package my.copter.facade.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.facade.statistic.DroneStatisticFacade;
import my.copter.persistence.elasticsearch.document.DroneSearchIndex;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DroneStatisticFacadeImpl implements DroneStatisticFacade {

    private final DronePDPSearchService service;
    private final CopterCrudService crudService;

    @Override
    public List<EventActivityDto> findAllStatistic() {
        return service.findAll()
                .stream()
                .map(e -> {
                    EventActivityDto dto = new EventActivityDto();
                    dto.setEvent(e.getUsername());
                    dto.setActivity(e.getDroneId());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<EventActivityDto> findAllUserStatistic() {
        return service.findAll()
                .stream()
                .collect(Collectors.groupingBy(DroneSearchIndex::getUsername, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    EventActivityDto dto = new EventActivityDto();
                    dto.setEvent(e.getKey());
                    dto.setActivity(e.getValue());
                    return dto;
                }).toList();
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
