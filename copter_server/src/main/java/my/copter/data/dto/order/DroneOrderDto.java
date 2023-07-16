package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

import my.copter.data.dto.product.BaseDto;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;

import org.apache.commons.collections4.CollectionUtils;

@Getter
@Setter
public class DroneOrderDto extends BaseDto {
    private String brand;
    private String name;
    private String image;
    private Long price;
    private Integer quantity;

    public DroneOrderDto(Copter copter) {
        this.setId(copter.getId());
        this.brand = copter.getBrand().toString();
        this.name = copter.getName();
        this.price = copter.getPrice();
        if (CollectionUtils.isNotEmpty(copter.getCopterImages())) {
            this.image = copter.getCopterImages()
                    .stream()
                    .filter(CopterImage::getMainImage)
                    .map(CopterImage::getImageUrl)
                    .findFirst()
                    .orElse("none");
        } else {
            this.image = "none";
        }
    }
}
