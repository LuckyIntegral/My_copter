package my.copter.service.aop.process.impl;

import lombok.AllArgsConstructor;

import my.copter.persistence.elasticsearch.document.DroneSearchIndex;
import my.copter.service.aop.process.DroneInfoSearchProcessService;
import my.copter.service.statistic.DronePDPSearchService;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class DroneInfoSearchProcessServiceImpl implements DroneInfoSearchProcessService {

    private final DronePDPSearchService service;

    @Override
    public void saveRequestToDronePdp(Long id) {
        DroneSearchIndex index = new DroneSearchIndex();
        index.setDroneId(id);
        index.setCreated(new Date());
        service.create(index);
    }
}
