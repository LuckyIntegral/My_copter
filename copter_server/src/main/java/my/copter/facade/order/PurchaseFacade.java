package my.copter.facade.order;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.order.PurchaseCreateDto;
import my.copter.data.dto.order.PurchaseDto;

public interface PurchaseFacade {
    void create(PurchaseCreateDto purchase);
    void update(Long id, Boolean active);
    DataTableResponse<PurchaseDto> findAll(DataTableRequest request);
}
