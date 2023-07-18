package my.copter.facade.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.data.dto.statistic.EventHistoryDto;
import my.copter.facade.statistic.DroneStatisticFacade;
import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
@AllArgsConstructor
public class DroneStatisticFacadeImpl implements DroneStatisticFacade {

    private final DronePDPSearchService service;
    private final CopterCrudService crudService;

    @Override
    public DataTableResponse<EventHistoryDto> findAllStatistic(DataTableRequest request) {
        Page<DroneStatisticIndex> page = service.findAll(request);
        DataTableResponse<EventHistoryDto> response = new DataTableResponse<>(request, page);
        response.setItems(page.get().map(EventHistoryDto::new).toList());
        return response;
    }

    @Override
    public List<EventActivityDto> findAllPdpStatistic() {
        return service.findAll()
                .stream()
                .collect(Collectors.groupingBy(DroneStatisticIndex::getDroneId, Collectors.counting()))
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
