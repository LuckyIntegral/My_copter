package my.copter.facade.index;

import my.copter.persistence.elasticsearch.document.DroneIndex;

import java.util.List;

public interface DroneInfoSearchFacade {
    List<DroneIndex> findAllByQuery(String query);
}
