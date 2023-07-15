package my.copter.persistence.sql.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static my.copter.persistence.sql.type.PermissionType.*;

@RequiredArgsConstructor
public enum RoleType {
    CUSTOMER(
            Set.of(
                    CUSTOMER_CREATE,
                    CUSTOMER_READ,
                    CUSTOMER_DELETE
            )),
    MANAGER(
            Set.of(
                    CUSTOMER_CREATE,
                    CUSTOMER_READ,
                    CUSTOMER_DELETE,
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )),
    ADMIN(
            Set.of(
                    CUSTOMER_CREATE,
                    CUSTOMER_READ,
                    CUSTOMER_DELETE,
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            ));

    @Getter
    private final Set<PermissionType> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
