package my.copter.facade.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.ImageDto;
import my.copter.facade.crud.CopterImageCrudFacade;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.service.crud.CopterImageCrudService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CopterImageCrudFacadeImpl implements CopterImageCrudFacade {

    private final CopterImageCrudService imageCrudService;

    @Override
    public void create(ImageDto entity) {
        CopterImage image = setFields(entity);
        imageCrudService.create(image);
    }

    @Override
    public void update(Long id, ImageDto entity) {
        CopterImage image = setFields(entity);
        image.setId(id);
        imageCrudService.update(image);
    }

    @Override
    public void delete(Long id) {
        imageCrudService.delete(id);
    }

    @Override
    public ImageDto findById(Long id) {
        return new ImageDto(imageCrudService.findById(id));
    }

    @Override
    public DataTableResponse<ImageDto> findAll(DataTableRequest request) {
        Page<CopterImage> images = imageCrudService.findAll(request);
        DataTableResponse<ImageDto> response = new DataTableResponse<>(request, images);
        List<ImageDto> items = images.get().map(ImageDto::new).toList();
        response.setItems(items);
        return response;
    }

    private CopterImage setFields(ImageDto entity) {
        CopterImage image = new CopterImage();
        image.setMainImage(entity.getMainImage());
        image.setImageUrl(entity.getImageUrl());
        return image;
    }
}
