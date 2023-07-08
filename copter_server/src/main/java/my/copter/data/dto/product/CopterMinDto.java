package my.copter.data.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import my.copter.persistence.sql.entity.product.Copter;

@Getter
@Setter
@NoArgsConstructor
public class CopterMinDto extends BaseDto {
    private String brand;
    private String name;
    private String cameraRes;
    private String category;

    public CopterMinDto(Copter copter) {
        this.setId(copter.getId());
        this.brand = copter.getBrand().getName();
        this.name = copter.getName();
        this.cameraRes = copter.getCameraResolution();
        this.category = copter.getCategoryType().getValue();
    }
}
