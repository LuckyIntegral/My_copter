package my.copter.controller.admin;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.users.UserDto;
import my.copter.data.response.DataContainer;
import my.copter.facade.user.UserActivityFacade;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/my/drone/admin/activity")
public class UserActivityController {

    private final UserActivityFacade activityFacade;

    @GetMapping
    public ResponseEntity<DataContainer<DataTableResponse<UserDto>>> findAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "id") String order
    ) {
        DataTableRequest request = new DataTableRequest();
        request.setPage(page);
        request.setSize(size);
        request.setOrder(order);
        return ResponseEntity.ok(new DataContainer<>(activityFacade.findAllUsers(request)));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<DataContainer<Boolean>> disableUser(@PathVariable Long id) {
        activityFacade.disableUserById(id);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<DataContainer<Boolean>> enableUser(@PathVariable Long id) {
        activityFacade.enableUserById(id);
        return ResponseEntity.ok(new DataContainer<>(Boolean.TRUE));
    }
}
