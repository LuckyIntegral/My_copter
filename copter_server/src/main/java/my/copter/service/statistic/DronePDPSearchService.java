package my.copter.service.statistic;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;

import org.springframework.data.domain.Page;

import java.util.List;

public interface DronePDPSearchService {
    void create(DroneStatisticIndex index);
    List<DroneStatisticIndex> findAll();
    Page<DroneStatisticIndex> findAll(DataTableRequest request);
}
