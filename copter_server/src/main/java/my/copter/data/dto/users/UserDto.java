package my.copter.data.dto.users;

import lombok.Getter;
import lombok.Setter;
import my.copter.persistence.sql.entity.user.User;

@Getter
@Setter
public class UserDto {
    private String fullName;
    private String username;
    private Boolean isEnabled;
    private String role;
    private Long id;

    public UserDto(User user) {
        this.fullName = user.getFullName();
        this.isEnabled = user.isEnabled();
        this.id = user.getId();
        this.role = user.getRoleType().name();
        this.username = user.getUsername();
    }
}
