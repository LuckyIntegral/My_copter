package my.copter.data.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CopterPdpDto {
    private String brand;
    private String name;
    private String description;
    private String cameraResolution;
    private String category;
    private Boolean fpv;
    private Long price;
    private String battery;
    private String flyTime;
    private List<String> images;

    public CopterPdpDto(Copter copter) {
        this.brand = copter.getBrand().toString();
        this.name = copter.getName();
        this.description = copter.getDescription();
        this.cameraResolution = copter.getCameraResolution();
        this.fpv = copter.getFpvCamera();
        this.category = copter.getCategoryType().toString();
        this.price = copter.getPrice();
        this.battery = copter.getBattery();
        this.flyTime = copter.getFlyTime();
        if (CollectionUtils.isNotEmpty(copter.getCopterImages())) {
            this.images = copter.getCopterImages()
                    .stream()
                    .map(CopterImage::getImageUrl)
                    .toList();
        } else {
            this.images = List.of("none");
        }
    }
}
