package my.copter.facade.statistic;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.data.dto.statistic.EventHistoryDto;

import java.util.List;

public interface DroneStatisticFacade {
    DataTableResponse<EventHistoryDto> findAllStatistic(DataTableRequest request);
    List<EventActivityDto> findAllPdpStatistic();
}
