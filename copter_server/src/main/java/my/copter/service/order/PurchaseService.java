package my.copter.service.order;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.sql.entity.order.Purchase;

import org.springframework.data.domain.Page;

public interface PurchaseService {
    void create(Purchase purchase);
    void update(Long id, Boolean active);
    Page<Purchase> findAll(DataTableRequest request);
}
