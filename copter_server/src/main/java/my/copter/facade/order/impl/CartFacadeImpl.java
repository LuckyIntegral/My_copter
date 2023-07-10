package my.copter.facade.order.impl;

import lombok.AllArgsConstructor;

import my.copter.data.dto.order.CartDto;
import my.copter.data.dto.order.CartEntryDto;
import my.copter.data.dto.order.DroneOrderDto;
import my.copter.facade.order.CartFacade;
import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.service.crud.CopterCrudService;
import my.copter.service.order.CartService;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartFacadeImpl implements CartFacade {
    
    private final CartService cartService;
    private final CopterCrudService copterCrudService;
    
    @Override
    public void addProduct(CartEntryDto dto) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setCopter(copterCrudService.findById(dto.getDroneId()));
        cartEntry.setQuantity(dto.getQuantity());
        cartService.addProduct(cartEntry);
    }

    @Override
    public void removeProduct(CartEntryDto dto) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setCopter(copterCrudService.findById(dto.getDroneId()));
        cartEntry.setQuantity(dto.getQuantity());
        cartService.removeProduct(cartEntry);
    }

    @Override
    public CartDto getActive() {
        Cart cart = cartService.getActive();
        CartDto dto = new CartDto();
        dto.setCreated(cart.getCreated());
        dto.setPrice(0L);
        List<CartEntry> entries = cartService.findAllByCart(cart);
        if (CollectionUtils.isNotEmpty(entries)) {
            dto.setDrones(entries
                    .stream()
                    .map(entry -> {
                        DroneOrderDto droneOrderDto = new DroneOrderDto(entry.getCopter());
                        droneOrderDto.setQuantity(entry.getQuantity());
                        dto.setPrice(dto.getPrice() + (droneOrderDto.getPrice() * droneOrderDto.getQuantity()));
                        return droneOrderDto;
                    }).toList());
        }
        return dto;
    }
}
