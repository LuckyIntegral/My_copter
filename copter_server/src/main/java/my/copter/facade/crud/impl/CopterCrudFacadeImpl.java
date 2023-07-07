package my.copter.facade.crud.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.CopterDto;
import my.copter.facade.crud.CopterCrudFacade;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;
import my.copter.service.crud.CopterCrudService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CopterCrudFacadeImpl implements CopterCrudFacade {

    private final CopterCrudService copterCrudService;

    @Override
    public void create(CopterDto entity) {
        Copter copter = setFields(entity);
        copterCrudService.create(copter);
    }

    @Override
    public void update(Long id, CopterDto entity) {
        Copter copter = setFields(entity);
        copter.setId(id);
        copterCrudService.update(copter);
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
    public DataTableResponse<CopterDto> findAll(DataTableRequest request) {
        Page<Copter> copters = copterCrudService.findAll(request);
        DataTableResponse<CopterDto> response = new DataTableResponse<>(request, copters);
        List<CopterDto> items = copters.get().map(CopterDto::new).toList();
        response.setItems(items);
        return response;
    }

    private Copter setFields(CopterDto entity) {
        Copter copter = new Copter();
        copter.setBrand(BrandType.valueOf(entity.getBrand()));
        copter.setName(entity.getName());
        copter.setDescription(entity.getDescription());
        copter.setCameraResolution(entity.getCameraResolution());
        copter.setFpvCamera(entity.getFpv());
        copter.setCategoryType(CategoryType.valueOf(entity.getCategory()));
        copter.setPrice(entity.getPrice());
        copter.setBattery(entity.getBattery());
        copter.setFlyTime(entity.getFlyTime());
        copter.setQuantity(entity.getQuantity());
        return copter;
    }
}
