package my.copter.persistence.elasticsearch.repository;

import my.copter.persistence.elasticsearch.document.DroneInfoIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneInfoIndexRepository extends ElasticsearchRepository<DroneInfoIndex, String> {
    List<DroneInfoIndex> findAllByDroneInfoContainingIgnoreCase(String query);
}
