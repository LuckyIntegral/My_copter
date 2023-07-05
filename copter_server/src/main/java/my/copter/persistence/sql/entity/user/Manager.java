package my.copter.persistence.sql.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import my.copter.persistence.sql.type.RoleType;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {
    public Manager() {
        super();
        setRoleType(RoleType.MANAGER);
    }
}
