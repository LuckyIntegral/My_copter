package my.copter.facade.user.impl;

import lombok.AllArgsConstructor;
import my.copter.facade.user.RoleFacade;
import my.copter.service.user.RoleService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleFacadeImpl implements RoleFacade {
    
    private final RoleService roleService;

    @Override
    public String getUserRole() {
        return roleService.getUserRole().toString();
    }
}
