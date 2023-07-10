package my.copter.service.search.impl;

import lombok.AllArgsConstructor;

import my.copter.persistence.elasticsearch.document.DroneInfoIndex;
import my.copter.persistence.elasticsearch.repository.DroneInfoIndexRepository;
import my.copter.service.search.DroneInfoSearchService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DroneInfoSearchServiceImpl implements DroneInfoSearchService {

    private final DroneInfoIndexRepository droneIndexRepository;

    @Override
    public List<DroneInfoIndex> findAllByQuery(String query) {
        return droneIndexRepository.findAllByDroneInfoContainingIgnoreCase(query);
    }
}
