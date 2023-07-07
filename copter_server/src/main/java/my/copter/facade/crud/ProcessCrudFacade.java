package my.copter.facade.crud;

import my.copter.data.dto.product.CopterIdImageIdDto;

public interface ProcessCrudFacade {
    void attachImage(CopterIdImageIdDto dto);
    void detachImage(CopterIdImageIdDto dto);
}
