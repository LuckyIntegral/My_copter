package my.copter.facade.pdp.impl;

import lombok.AllArgsConstructor;
import my.copter.data.dto.product.CopterPdpDto;
import my.copter.facade.pdp.DronePDPFacade;
import my.copter.service.crud.CopterCrudService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DronePDPFacadeImpl implements DronePDPFacade {

    private final CopterCrudService copterCrudService;

    @Override
    public CopterPdpDto findById(Long id) {
        return new CopterPdpDto(copterCrudService.findById(id));
    }
}
