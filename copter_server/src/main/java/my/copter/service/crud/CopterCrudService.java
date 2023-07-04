package my.copter.service.crud;

import my.copter.persistance.sql.entity.product.Copter;

public interface CopterCrudService extends CrudService<Copter> {
    void attachImage(Long copterId, Long copterImageId);
    void detachImage(Long copterId, Long copterImageId);
}
