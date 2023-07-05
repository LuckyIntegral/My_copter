package my.copter.service.crud;

import my.copter.persistence.sql.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
