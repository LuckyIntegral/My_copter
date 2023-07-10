package my.copter.service.statistic;

import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;

import java.util.List;

public interface DronePDPSearchService {
    void create(DroneStatisticIndex index);
    List<DroneStatisticIndex> findAll();
}
