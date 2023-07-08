package my.copter.facade.plp.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.CopterPlpDto;
import my.copter.facade.plp.DronePLPFacade;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.service.crud.CopterCrudService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DronePLPFacadeImpl implements DronePLPFacade {

    private final CopterCrudService copterCrudService;

    @Override
    public DataTableResponse<CopterPlpDto> findAll(DataTableRequest request, String brand, String category) {
        Page<Copter> copters;
        if (!brand.equalsIgnoreCase("all")) {
            copters = copterCrudService.findAllByBrand(brand, request);
        } else if (!category.equalsIgnoreCase("all")) {
            copters = copterCrudService.findAllByCategory(category, request);
        } else {
            copters = copterCrudService.findAll(request);
        }
        DataTableResponse<CopterPlpDto> response = new DataTableResponse<>(request, copters);
        List<CopterPlpDto> items = copters.get().map(CopterPlpDto::new).toList();
        response.setItems(items);
        return response;
    }
}
