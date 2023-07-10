package my.copter.facade.statistic;

import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.data.dto.statistic.EventHistoryDto;

import java.util.List;

public interface DroneStatisticFacade {
    List<EventHistoryDto> findAllStatistic();
    List<EventActivityDto> findAllPdpStatistic();
}
