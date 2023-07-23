package my.copter.persistence.sql.repository.order;

import my.copter.persistence.sql.entity.order.Purchase;
import my.copter.persistence.sql.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends BaseRepository<Purchase> {
}
