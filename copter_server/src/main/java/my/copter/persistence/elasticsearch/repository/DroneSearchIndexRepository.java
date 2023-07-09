package my.copter.persistence.elasticsearch.repository;

import my.copter.persistence.elasticsearch.document.DroneSearchIndex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneSearchIndexRepository extends ElasticsearchRepository<DroneSearchIndex, String> { }
