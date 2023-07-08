package my.copter.controller.open;

import lombok.AllArgsConstructor;
import my.copter.data.dto.product.CopterPdpDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.pdp.DronePDPFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/my/drone/open/pdp")
public class DronePDPController {

    private final DronePDPFacade dronePDPFacade;

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<CopterPdpDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(dronePDPFacade.findById(id)));
    }
}
