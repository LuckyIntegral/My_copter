package my.copter.persistence.sql.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import my.copter.persistence.sql.type.RoleType;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    public Admin() {
        super();
        setRoleType(RoleType.ADMIN);
    }
}
