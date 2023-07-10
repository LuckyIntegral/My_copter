package my.copter.persistence.sql.repository.order;

import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.repository.BaseRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartEntryRepository extends BaseRepository<CartEntry> {
    List<CartEntry> findAllByCart(Cart cart);
    Optional<CartEntry> findCartEntryByCartAndCopter(Cart cart, Copter copter);
}
