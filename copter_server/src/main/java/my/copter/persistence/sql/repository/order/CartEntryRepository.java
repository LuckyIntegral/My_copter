package my.copter.persistence.sql.repository.order;

import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.order.CartEntry;
import my.copter.persistence.sql.repository.BaseRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartEntryRepository extends BaseRepository<CartEntry> {
    List<CartEntry> findAllByCart(Cart cart);
}
