package my.copter.controller.open;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.CopterPlpDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.plp.DronePLPFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/my/drone/open/plp")
public class DronePLPController {

    private final DronePLPFacade dronePLPFacade;

    @GetMapping
    public ResponseEntity<DataContainer<DataTableResponse<CopterPlpDto>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "all") String brand,
            @RequestParam(defaultValue = "all") String category
    ) {
        DataTableRequest request = new DataTableRequest();
        request.setPage(page);
        request.setSize(size);
        request.setOrder(order);
        return ResponseEntity.ok(new DataContainer<>(dronePLPFacade.findAll(request, brand, category)));
    }
}
