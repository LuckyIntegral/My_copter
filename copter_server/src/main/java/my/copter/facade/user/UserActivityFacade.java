package my.copter.facade.user;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.users.UserDto;

public interface UserActivityFacade {
    DataTableResponse<UserDto> findAllUsers(DataTableRequest request);
    void disableUserById(Long id);
    void enableUserById(Long id);
}
