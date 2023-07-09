package my.copter.service.search.impl;

import lombok.AllArgsConstructor;

import my.copter.persistence.elasticsearch.document.DroneIndex;
import my.copter.persistence.elasticsearch.repository.DroneIndexRepository;
import my.copter.service.search.DroneInfoSearchService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DroneInfoSearchServiceImpl implements DroneInfoSearchService {

    private final DroneIndexRepository droneIndexRepository;

    @Override
    public List<DroneIndex> findAllByQuery(String query) {
        return droneIndexRepository.findAllByDroneInfoContainingIgnoreCase(query);
    }
}
