package my.copter.service.order;

import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;

import java.util.List;

public interface CartService {
    void addProduct(CartEntry cartEntry);
    void removeProduct(CartEntry cartEntry);
    Cart getActive();
    List<CartEntry> findAllByCart(Cart cart);
}
