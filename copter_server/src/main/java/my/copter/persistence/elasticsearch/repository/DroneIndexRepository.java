package my.copter.persistence.elasticsearch.repository;

import my.copter.persistence.elasticsearch.document.DroneIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneIndexRepository extends ElasticsearchRepository<DroneIndex, String> {
    List<DroneIndex> findAllByDroneInfoContainingIgnoreCase(String query);
}
