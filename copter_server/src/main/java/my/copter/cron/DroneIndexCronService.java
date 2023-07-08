package my.copter.cron;

import lombok.AllArgsConstructor;

import my.copter.data.dto.product.CopterMinDto;
import my.copter.persistence.elasticsearch.document.DroneIndex;
import my.copter.persistence.elasticsearch.repository.DroneIndexRepository;
import my.copter.persistence.sql.repository.product.CopterRepository;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class DroneIndexCronService {

    private final DroneIndexRepository droneIndexRepository;
    private final CopterRepository copterRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Scheduled(cron = "*/10 * * * * *")
    public void syncToElastic() {
        elasticsearchOperations.indexOps(DroneIndex.class).refresh();
        droneIndexRepository.deleteAll();
        droneIndexRepository.saveAll(generate());
    }

    private Collection<DroneIndex> generate() {
        Collection<DroneIndex> indices = new ArrayList<>();
        Collection<CopterMinDto> collection = copterRepository.findMinDto();
        if (CollectionUtils.isNotEmpty(collection)) {
            indices = collection
                    .stream()
                    .map(drone -> {
                        DroneIndex droneIndex = new DroneIndex();
                        String builder = drone.getBrand() +
                                ", " +
                                drone.getName() +
                                ", " +
                                drone.getCameraRes() +
                                ", " +
                                drone.getCategory();
                        droneIndex.setDroneInfo(builder);
                        droneIndex.setDroneId(drone.getId());
                        return droneIndex;
                    })
                    .toList();
        }
        return indices;
    }
}
