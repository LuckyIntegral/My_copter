package my.copter.facade.index;

import my.copter.persistence.elasticsearch.document.DroneInfoIndex;

import java.util.List;

public interface DroneInfoSearchFacade {
    List<DroneInfoIndex> findAllByQuery(String query);
}
