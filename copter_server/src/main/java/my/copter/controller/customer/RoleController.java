package my.copter.controller.customer;

import lombok.AllArgsConstructor;
import my.copter.data.response.DataContainer;
import my.copter.facade.user.RoleFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/my/drone/customer/role")
public class RoleController {

    private final RoleFacade roleFacade;

    @GetMapping
    @PreAuthorize("hasAuthority('personal:read')")
    public ResponseEntity<DataContainer<String>> getRoleType() {
        return ResponseEntity.ok(new DataContainer<>(roleFacade.getUserRole()));
    }
}
