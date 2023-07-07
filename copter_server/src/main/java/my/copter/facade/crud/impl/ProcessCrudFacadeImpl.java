package my.copter.facade.crud.impl;

import lombok.AllArgsConstructor;
import my.copter.data.dto.product.CopterIdImageIdDto;
import my.copter.facade.crud.ProcessCrudFacade;
import my.copter.service.crud.CopterCrudService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcessCrudFacadeImpl implements ProcessCrudFacade {

    private final CopterCrudService copterCrudService;

    @Override
    public void attachImage(CopterIdImageIdDto dto) {
        copterCrudService.attachImage(dto.getCopterId(), dto.getImageId());
    }

    @Override
    public void detachImage(CopterIdImageIdDto dto) {
        copterCrudService.detachImage(dto.getCopterId(), dto.getImageId());
    }
}
