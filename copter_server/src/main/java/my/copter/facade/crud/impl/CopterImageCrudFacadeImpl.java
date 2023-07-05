package my.copter.facade.crud.impl;

import lombok.AllArgsConstructor;
import my.copter.data.dto.product.ImageDto;
import my.copter.facade.crud.CopterImageCrudFacade;
import my.copter.persistence.sql.entity.product.CopterImage;
import my.copter.service.crud.CopterImageCrudService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CopterImageCrudFacadeImpl implements CopterImageCrudFacade {

    private final CopterImageCrudService imageCrudService;

    @Override
    public void create(ImageDto entity) {
        CopterImage image = setFields(entity, new CopterImage());
        imageCrudService.create(image);
    }

    @Override
    public void update(Long id, ImageDto entity) {
        CopterImage image = setFields(entity, imageCrudService.findById(id));
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
    public Collection<ImageDto> findAll() {
        return imageCrudService.findAll()
                .stream()
                .map(ImageDto::new)
                .toList();
    }

    private CopterImage setFields(ImageDto entity, CopterImage image) {
        image.setMainImage(entity.getMainImage());
        image.setImageUrl(entity.getImageUrl());
        return image;
    }
}
