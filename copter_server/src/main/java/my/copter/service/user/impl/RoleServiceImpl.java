package my.copter.service.user.impl;

import lombok.AllArgsConstructor;

import my.copter.exception.UserNotFoundException;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.user.UserRepository;
import my.copter.persistence.sql.type.RoleType;
import my.copter.service.user.RoleService;
import my.copter.util.SecurityUtil;

import org.springframework.stereotype.Component;

import static my.copter.util.ExceptionUtil.USER_NOT_FOUND;

@Component
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRepository<User> userUserRepository;

    @Override
    public RoleType getUserRole() {
        return userUserRepository.findByUsername(SecurityUtil.getUserName())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND))
                .getRoleType();
    }
}
