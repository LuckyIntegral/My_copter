package my.copter.controller.manager;

import lombok.AllArgsConstructor;

import my.copter.data.dto.product.CopterDto;
import my.copter.data.dto.product.CopterIdImageIdDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.crud.CopterCrudFacade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequestMapping("/my/drone/manager/copter")
public class CopterCrudController {

    private final CopterCrudFacade copterCrudFacade;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create', 'manager:create')")
    public ResponseEntity<DataContainer<Boolean>> createCopter(@RequestBody CopterDto dto) {
        copterCrudFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(Boolean.TRUE));
    }

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

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update', 'manager:update')")
    public ResponseEntity<DataContainer<Boolean>> updateCopter(@PathVariable Long id, @RequestBody CopterDto dto) {
        copterCrudFacade.update(id, dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete', 'manager:delete')")
    public ResponseEntity<DataContainer<Boolean>> deleteCopter(@PathVariable Long id) {
        copterCrudFacade.delete(id);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'manager:read')")
    public ResponseEntity<DataContainer<CopterDto>> findByIdCopter(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(copterCrudFacade.findById(id)));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'manager:read')")
    public ResponseEntity<DataContainer<Collection<CopterDto>>> findAllCopters() {
        return ResponseEntity.ok(new DataContainer<>(copterCrudFacade.findAll()));
    }
}
