package my.copter.service.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.persistence.elasticsearch.document.DroneSearchIndex;
import my.copter.persistence.elasticsearch.repository.DroneSearchIndexRepository;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class DronePDPSearchServiceImpl implements DronePDPSearchService {

    private final DroneSearchIndexRepository repository;

    @Override
    public void create(DroneSearchIndex index) {
        repository.save(index);
    }

    @Override
    public List<DroneSearchIndex> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(DroneSearchIndex::getCreated))
                .toList();
    }
}
