package my.copter.service.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;
import my.copter.persistence.elasticsearch.repository.DroneStatisticIndexRepository;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class DronePDPSearchServiceImpl implements DronePDPSearchService {

    private final DroneStatisticIndexRepository repository;

    @Override
    public void create(DroneStatisticIndex index) {
        repository.save(index);
    }

    @Override
    public List<DroneStatisticIndex> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(DroneStatisticIndex::getCreated))
                .toList();
    }
}
