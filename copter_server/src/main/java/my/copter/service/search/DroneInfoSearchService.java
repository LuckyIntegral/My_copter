package my.copter.service.search;

import my.copter.persistence.elasticsearch.document.DroneInfoIndex;

import java.util.List;

public interface DroneInfoSearchService {
    List<DroneInfoIndex> findAllByQuery(String query);
}
