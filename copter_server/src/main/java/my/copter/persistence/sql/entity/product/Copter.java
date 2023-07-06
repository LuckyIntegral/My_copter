package my.copter.persistence.sql.entity.product;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import my.copter.persistence.sql.entity.BaseEntity;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "copters")
public class Copter extends BaseEntity {

    @Column(name = "brand", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private BrandType brand;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 800)
    private String description;

    @Column(name = "camera_resolution", nullable = false)
    private String cameraResolution;

    @Column(name = "fpv_camera")
    private Boolean fpvCamera;

    @Column(name = "category_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "battery", nullable = false)
    private String battery;

    @Column(name = "fly_time", nullable = false)
    private String flyTime;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToMany
    @JoinTable(
            name = "copter_image",
            joinColumns = @JoinColumn(name = "copter_id"),
            inverseJoinColumns = @JoinColumn(name = "copter_image_id")
    )
    private Set<CopterImage> copterImages;

    public Copter() {
        super();
        this.fpvCamera = false;
        this.copterImages = new HashSet<>();
    }
}
