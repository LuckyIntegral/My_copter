package my.copter.persistence.sql.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.sql.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase extends BaseEntity {

    @Column(nullable = false)
    private String contact;

    @Column
    private String address;

    @Column
    private Boolean actual;

    @OneToOne
    private Cart cart;

    public Purchase() {
        super();
        this.actual = true;
    }
}
