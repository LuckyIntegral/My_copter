package my.copter.facade.crud;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.BaseDto;

public interface CrudFacade<E extends BaseDto> {
    void create(E entity);
    void update(Long id, E entity);
    void delete(Long id);
    E findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);
}
