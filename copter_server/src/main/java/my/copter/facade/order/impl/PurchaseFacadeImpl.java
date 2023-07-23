package my.copter.facade.order.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.order.DroneQuantityDto;
import my.copter.data.dto.order.PurchaseCreateDto;
import my.copter.data.dto.order.PurchaseDto;
import my.copter.facade.order.PurchaseFacade;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.entity.order.Purchase;
import my.copter.service.order.CartService;
import my.copter.service.order.PurchaseService;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseFacadeImpl implements PurchaseFacade {

    private final PurchaseService purchaseService;
    private final CartService cartService;

    @Override
    public void create(PurchaseCreateDto dto) {
        Purchase purchase = new Purchase();
        purchase.setCart(cartService.findCartById(dto.getCartId()));
        purchase.setAddress(dto.getAddress());
        purchase.setContact(dto.getContact());
        purchaseService.create(purchase);
    }

    @Override
    public void update(Long id, Boolean active) {
        purchaseService.update(id, active);
    }

    @Override
    public DataTableResponse<PurchaseDto> findAll(DataTableRequest request) {
        Page<Purchase> purchases = purchaseService.findAll(request);
        DataTableResponse<PurchaseDto> response = new DataTableResponse<>(request, purchases);
        List<PurchaseDto> items = purchases.get().map(purchase -> {
            PurchaseDto dto = new PurchaseDto();
            dto.setUsername(purchase.getCart().getOwner().getUsername());
            dto.setContact(purchase.getContact());
            dto.setAddress(purchase.getAddress());
            dto.setActive(purchase.getActual());
            dto.setCartId(purchase.getCart().getId());
            dto.setPrice(0L);
            dto.setId(purchase.getId());
            List<CartEntry> entries = cartService.findAllByCart(purchase.getCart());
            if (CollectionUtils.isNotEmpty(entries))
                for (CartEntry entry : entries)
                {
                    if (entry.getQuantity() == 0) continue;
                    DroneQuantityDto dqd = new DroneQuantityDto();
                    dqd.setName(entry.getCopter().getName());
                    dqd.setDroneId(entry.getCopter().getId());
                    dqd.setQuantity(entry.getQuantity());
                    dto.getDrones().add(dqd);
                    dto.setPrice(dto.getPrice() + (entry.getCopter().getPrice() * entry.getQuantity()));
                }
            return dto;
        }).sorted((a, b) -> a.getActive() && b.getActive() ? 0 : a.getActive() ? -1 : 1).toList();
        response.setItems(items);
        return response;
    }
}
