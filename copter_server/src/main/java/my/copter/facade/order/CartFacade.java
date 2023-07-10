package my.copter.facade.order;

import my.copter.data.dto.order.CartDto;
import my.copter.data.dto.order.CartEntryDto;

public interface CartFacade {
    void addProduct(CartEntryDto dto);
    void removeProduct(CartEntryDto dto);
    CartDto getActive();
}
