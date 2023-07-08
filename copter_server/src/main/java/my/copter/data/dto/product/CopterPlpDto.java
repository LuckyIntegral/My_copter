package my.copter.data.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.entity.product.CopterImage;
import org.apache.commons.collections4.CollectionUtils;

@Getter
@Setter
@NoArgsConstructor
public class CopterPlpDto extends BaseDto {
    private String brand;
    private String name;
    private String image;
    private Long price;

    public CopterPlpDto(Copter copter) {
        this.setId(copter.getId());
        this.brand = copter.getBrand().toString();
        this.name = copter.getName();
        this.price = copter.getPrice();
        if (CollectionUtils.isNotEmpty(copter.getCopterImages())) {
            this.image = copter.getCopterImages()
                    .stream()
                    .filter(CopterImage::getMainImage)
                    .map(Object::toString)
                    .findFirst()
                    .orElse("none");
        } else {
            this.image = "none";
        }
    }
}
