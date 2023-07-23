package my.copter.controller.customer;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.order.PurchaseCreateDto;
import my.copter.data.dto.order.PurchaseDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.order.PurchaseFacade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/my/drone/customer/order")
public class OrderController {

    private final PurchaseFacade purchaseFacade;

    @PostMapping
    @PreAuthorize("hasAuthority('personal:create')")
    public ResponseEntity<DataContainer<Boolean>> createPurchase(@RequestBody PurchaseCreateDto dto) {
        purchaseFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(Boolean.TRUE));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('manager:update', 'admin:update')")
    public ResponseEntity<DataContainer<Boolean>> updatePurchase(@PathVariable Long id, @RequestBody Boolean active) {
        purchaseFacade.update(id, active);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('manager:read', 'admin:read')")
    public ResponseEntity<DataContainer<DataTableResponse<PurchaseDto>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "id") String order
    ) {
        DataTableRequest request = new DataTableRequest();
        request.setPage(page);
        request.setSize(size);
        request.setOrder(order);
        return ResponseEntity.ok(new DataContainer<>(purchaseFacade.findAll(request)));
    }
}
