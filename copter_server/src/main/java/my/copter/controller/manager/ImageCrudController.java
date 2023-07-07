package my.copter.controller.manager;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.ImageDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.crud.CopterImageCrudFacade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequestMapping("/my/drone/manager/image")
public class ImageCrudController {

    private final CopterImageCrudFacade imageCrudFacade;

    @PostMapping
    public ResponseEntity<DataContainer<Boolean>> createImage(@RequestBody ImageDto dto) {
        imageCrudFacade.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataContainer<>(Boolean.TRUE));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> updateImage(@PathVariable Long id, @RequestBody ImageDto dto) {
        imageCrudFacade.update(id, dto);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataContainer<Boolean>> deleteImage(@PathVariable Long id) {
        imageCrudFacade.delete(id);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataContainer<ImageDto>> findImageById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataContainer<>(imageCrudFacade.findById(id)));
    }

    @GetMapping()
    public ResponseEntity<DataContainer<DataTableResponse<ImageDto>>> findAllImages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "id") String order
    ) {
        DataTableRequest request = new DataTableRequest();
        request.setPage(page);
        request.setSize(size);
        request.setOrder(order);
        return ResponseEntity.ok(new DataContainer<>(imageCrudFacade.findAll(request)));
    }
}
