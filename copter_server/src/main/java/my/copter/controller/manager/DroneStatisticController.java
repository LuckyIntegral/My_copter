package my.copter.controller.manager;

import lombok.AllArgsConstructor;

import my.copter.data.dto.statistic.EventActivityDto;
import my.copter.data.dto.statistic.EventHistoryDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.statistic.DroneStatisticFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequestMapping("/my/drone/manager/statistic")
public class DroneStatisticController {

    private final DroneStatisticFacade droneStatisticFacade;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'manager:read')")
    public ResponseEntity<DataContainer<List<EventHistoryDto>>> findAllStatistic() {
        return ResponseEntity.ok(new DataContainer<>(droneStatisticFacade.findAllStatistic()));
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyAuthority('admin:read', 'manager:read')")
    public ResponseEntity<DataContainer<List<EventActivityDto>>> findAllPdpStatistic() {
        return ResponseEntity.ok(new DataContainer<>(droneStatisticFacade.findAllPdpStatistic()));
    }
}
