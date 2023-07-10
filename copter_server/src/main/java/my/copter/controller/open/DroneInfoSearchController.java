package my.copter.controller.open;

import lombok.AllArgsConstructor;

import my.copter.data.response.DataContainer;
import my.copter.facade.index.DroneInfoSearchFacade;
import my.copter.persistence.elasticsearch.document.DroneInfoIndex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/my/drone/open/search")
public class DroneInfoSearchController {

    private final DroneInfoSearchFacade droneInfoSearchFacade;

    @GetMapping
    public ResponseEntity<DataContainer<Collection<DroneInfoIndex>>> findAllByQuery(@RequestParam String query) {
        return ResponseEntity.ok(new DataContainer<>(droneInfoSearchFacade.findAllByQuery(query)));
    }
}
