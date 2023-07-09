package my.copter.facade.statistic;

import my.copter.data.dto.statistic.EventActivityDto;

import java.util.List;

public interface DroneStatisticFacade {
    List<EventActivityDto> findAllStatistic();
    List<EventActivityDto> findAllUserStatistic();
    List<EventActivityDto> findAllPdpStatistic();
}
