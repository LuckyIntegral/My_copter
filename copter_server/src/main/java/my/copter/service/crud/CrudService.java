package my.copter.service.crud;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.sql.entity.BaseEntity;
import org.springframework.data.domain.Page;

public interface CrudService<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Page<E> findAll(DataTableRequest request);
}
