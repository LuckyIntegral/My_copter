package my.copter.service.statistic;

import my.copter.persistence.elasticsearch.document.DroneSearchIndex;

import java.util.List;

public interface DronePDPSearchService {
    void create(DroneSearchIndex index);
    List<DroneSearchIndex> findAll();
}
