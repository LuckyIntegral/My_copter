package my.copter.persistence.elasticsearch.repository;

import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneStatisticIndexRepository extends ElasticsearchRepository<DroneStatisticIndex, String> { }
