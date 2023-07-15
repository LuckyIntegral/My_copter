package my.copter.controller.admin;

import lombok.AllArgsConstructor;

import my.copter.config.security.service.AuthenticationService;
import my.copter.config.security.dto.RegisterRequest;
import my.copter.persistence.sql.type.RoleType;
import my.copter.data.response.DataContainer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/my/drone/admin")
public class ManagerCreateController {

    private final AuthenticationService service;

    @PostMapping("/personal")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<DataContainer<Boolean>> createManager(@RequestBody RegisterRequest request) {
        request.setRoleType(RoleType.MANAGER);
        service.register(request);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }
}
