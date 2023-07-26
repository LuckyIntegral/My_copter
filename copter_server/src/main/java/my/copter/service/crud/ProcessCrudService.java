package my.copter.service.crud;

public interface ProcessCrudService {
    void attachImage(Long copterId, Long copterImageId);
    void detachImage(Long copterId, Long copterImageId);
}
