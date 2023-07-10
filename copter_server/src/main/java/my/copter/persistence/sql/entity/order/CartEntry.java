package my.copter.persistence.sql.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.sql.entity.BaseEntity;
import my.copter.persistence.sql.entity.product.Copter;

@Getter
@Setter
@Entity
@Table(name = "cart_entries")
public class CartEntry extends BaseEntity {

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Copter copter;

    @Column
    private Integer quantity;

    public CartEntry() {
        this.quantity = 0;
    }
}
