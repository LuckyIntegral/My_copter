package my.copter.data.dto.product;

import lombok.Getter;
import lombok.Setter;
import my.copter.persistence.sql.entity.product.Copter;

@Getter
@Setter
public class CopterDto {
    private String brand;
    private String name;
    private String description;
    private String cameraResolution;
    private Boolean fpv;
    private String category;
    private Long price;
    private String battery;
    private String flyTime;

    public CopterDto(Copter copter) {
        this.brand = copter.getBrand().toString();
        this.name = copter.getName();
        this.description = copter.getDescription();
        this.cameraResolution = copter.getCameraResolution();
        this.fpv = copter.getFpvCamera();
        this.category = copter.getCategoryType().toString();
        this.price = copter.getPrice();
        this.battery = copter.getBattery();
        this.flyTime = copter.getFlyTime();
    }
}
