package my.copter.persistence.sql.repository.order;

import my.copter.persistence.sql.entity.order.Cart;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.BaseRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends BaseRepository<Cart> {
    Optional<Cart> findByOwnerAndActiveTrue(User owner);
}
