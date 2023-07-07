package my.copter.controller.manager;

import lombok.AllArgsConstructor;

import my.copter.data.dto.product.CopterIdImageIdDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.crud.ProcessCrudFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequestMapping("/my/drone/manager/process")
public class CopterProcessController {

    private final ProcessCrudFacade processCrudFacade;

    @PutMapping("/attach")
    @PreAuthorize("hasAnyAuthority('admin:update', 'manager:update')")
    public ResponseEntity<DataContainer<Boolean>> addImageToCopter(@RequestBody CopterIdImageIdDto dto) {
        processCrudFacade.attachImage(dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @PutMapping("/detach")
    @PreAuthorize("hasAnyAuthority('admin:update', 'manager:update')")
    public ResponseEntity<DataContainer<Boolean>> deleteImageFromCopter(@RequestBody CopterIdImageIdDto dto) {
        processCrudFacade.detachImage(dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }
}

