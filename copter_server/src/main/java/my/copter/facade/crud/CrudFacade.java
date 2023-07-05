package my.copter.facade.crud;

import java.util.Collection;

public interface CrudFacade<E> {
    void create(E entity);
    void update(Long id, E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
