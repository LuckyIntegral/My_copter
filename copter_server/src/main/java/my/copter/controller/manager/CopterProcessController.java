package my.copter.controller.manager;

import lombok.AllArgsConstructor;

import my.copter.data.dto.product.CopterIdImageIdDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.crud.CopterCrudFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequestMapping("/my/drone/manager/process")
public class CopterProcessController {

    private final CopterCrudFacade copterCrudFacade;

    @PostMapping("/attach")
    @PreAuthorize("hasAnyAuthority('admin:create', 'manager:create')")
    public ResponseEntity<DataContainer<Boolean>> addImageToCopter(@RequestBody CopterIdImageIdDto dto) {
        copterCrudFacade.attachImage(dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @DeleteMapping("/detach")
    @PreAuthorize("hasAnyAuthority('admin:delete', 'manager:delete')")
    public ResponseEntity<DataContainer<Boolean>> deleteImageFromCopter(@RequestBody CopterIdImageIdDto dto) {
        copterCrudFacade.detachImage(dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }
}

