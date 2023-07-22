package my.copter.facade.user.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.users.UserDto;
import my.copter.facade.user.UserActivityFacade;
import my.copter.persistence.sql.entity.user.User;
import my.copter.service.user.UserActivityService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityFacadeImpl implements UserActivityFacade {

    private final UserActivityService activityService;

    @Override
    public DataTableResponse<UserDto> findAllUsers(DataTableRequest request) {
        Page<User> users;
        users = activityService.findAllUsers(request);
        DataTableResponse<UserDto> response = new DataTableResponse<>(request, users);
        List<UserDto> items = users.get().map(UserDto::new).toList();
        response.setItems(items);
        return response;
    }

    @Override
    public void disableUserById(Long id) {
        activityService.disableUserById(id);
    }

    @Override
    public void enableUserById(Long id) {
        activityService.enableUserById(id);
    }
}
