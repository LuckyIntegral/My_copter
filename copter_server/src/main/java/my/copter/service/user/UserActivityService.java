package my.copter.service.user;

import my.copter.data.datatable.DataTableRequest;
import my.copter.persistence.sql.entity.user.User;

import org.springframework.data.domain.Page;

public interface UserActivityService {
    Page<User> findAllUsers(DataTableRequest request);
    void disableUserById(Long id);
    void enableUserById(Long id);
}
