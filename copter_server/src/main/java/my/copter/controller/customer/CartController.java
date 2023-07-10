package my.copter.controller.customer;

import lombok.AllArgsConstructor;

import my.copter.data.dto.order.CartDto;
import my.copter.data.dto.order.CartEntryDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.order.CartFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/my/drone/customer/cart")
public class CartController {

    private final CartFacade cartFacade;

    @GetMapping
    @PreAuthorize("hasAuthority('personal:read')")
    public ResponseEntity<DataContainer<CartDto>> getCart() {
        return ResponseEntity.ok(new DataContainer<>(cartFacade.getActive()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('personal:create')")
    public ResponseEntity<DataContainer<Boolean>> addDrone(@RequestBody CartEntryDto cartEntryDto) {
        cartFacade.addProduct(cartEntryDto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('personal:delete')")
    public ResponseEntity<DataContainer<Boolean>> removeDrone(@RequestBody CartEntryDto cartEntryDto) {
        cartFacade.removeProduct(cartEntryDto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }
}
