package my.copter.persistence.sql.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import my.copter.persistence.sql.type.RoleType;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    public Customer() {
        super();
        setRoleType(RoleType.CUSTOMER);
    }
}
