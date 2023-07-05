package my.copter.facade.crud;

import my.copter.data.dto.product.CopterDto;
import my.copter.data.dto.product.CopterIdImageIdDto;

public interface CopterCrudFacade extends CrudFacade<CopterDto> {
    void attachImage(CopterIdImageIdDto dto);
    void detachImage(CopterIdImageIdDto dto);
}
