package my.copter.facade.pdp;

import my.copter.data.dto.product.CopterPdpDto;

public interface DronePDPFacade {
    CopterPdpDto findById(Long id);
}
