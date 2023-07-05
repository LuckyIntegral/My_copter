package my.copter.facade.crud.impl;

import lombok.AllArgsConstructor;
import my.copter.data.dto.product.CopterDto;
import my.copter.data.dto.product.CopterIdImageIdDto;
import my.copter.facade.crud.CopterCrudFacade;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;
import my.copter.service.crud.CopterCrudService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CopterCrudFacadeImpl implements CopterCrudFacade {

    private final CopterCrudService copterCrudService;

    @Override
    public void attachImage(CopterIdImageIdDto dto) {
        copterCrudService.attachImage(dto.getCopterId(), dto.getImageId());
    }

    @Override
    public void detachImage(CopterIdImageIdDto dto) {
        copterCrudService.detachImage(dto.getCopterId(), dto.getImageId());
    }

    @Override
    public void create(CopterDto entity) {
        Copter copter = setFields(entity, new Copter());
        copterCrudService.create(copter);
    }

    @Override
    public void update(Long id, CopterDto entity) {
        Copter copter = setFields(entity, copterCrudService.findById(id));
        copterCrudService.create(copter);
    }

    @Override
    public void delete(Long id) {
        copterCrudService.delete(id);
    }

    @Override
    public CopterDto findById(Long id) {
        return new CopterDto(copterCrudService.findById(id));
    }

    @Override
    public Collection<CopterDto> findAll() {
        return copterCrudService.findAll()
                .stream()
                .map(CopterDto::new)
                .toList();
    }

    private Copter setFields(CopterDto entity, Copter copter) {
        copter.setBrand(BrandType.valueOf(entity.getBrand()));
        copter.setName(entity.getName());
        copter.setDescription(entity.getDescription());
        copter.setCameraResolution(entity.getCameraResolution());
        copter.setFpvCamera(entity.getFpv());
        copter.setCategoryType(CategoryType.valueOf(entity.getCategory()));
        copter.setPrice(entity.getPrice());
        copter.setBattery(entity.getBattery());
        copter.setFlyTime(entity.getFlyTime());
        return copter;
    }
}
