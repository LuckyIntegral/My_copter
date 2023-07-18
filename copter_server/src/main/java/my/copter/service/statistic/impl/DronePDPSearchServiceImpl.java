package my.copter.service.statistic.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.elasticsearch.document.DroneStatisticIndex;
import my.copter.persistence.elasticsearch.repository.DroneStatisticIndexRepository;
import my.copter.service.statistic.DronePDPSearchService;
import my.copter.util.PersistenceUtil;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@Service
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

    @Override
    public Page<DroneStatisticIndex> findAll(DataTableRequest request) {
        return repository.findAll(PersistenceUtil.generatePageableByDataTableRequest(request));
    }
}
