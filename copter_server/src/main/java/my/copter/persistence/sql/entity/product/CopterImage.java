package my.copter.persistence.sql.entity.product;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.sql.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "copter_images")
public class CopterImage extends BaseEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "mainImage", nullable = false)
    private Boolean mainImage;

    @ManyToMany(mappedBy = "copterImages")
    private Set<Copter> copters;

    public CopterImage() {
        super();
        this.mainImage = false;
        this.copters = new HashSet<>();
    }
}
