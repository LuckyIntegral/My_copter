package my.copter.persistence.sql.entity.order;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.sql.entity.BaseEntity;
import my.copter.persistence.sql.entity.user.Customer;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created;

    @ManyToOne
    private Customer owner;

    public Cart() {
        this.created= new Date();
        this.active = true;
    }
}
