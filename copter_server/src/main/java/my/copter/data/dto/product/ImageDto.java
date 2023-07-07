package my.copter.data.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import my.copter.persistence.sql.entity.product.CopterImage;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto extends BaseDto {
    private String imageUrl;
    private Boolean mainImage;

    public ImageDto(CopterImage image) {
        this.setId(image.getId());
        this.imageUrl = image.getImageUrl();
        this.mainImage = image.getMainImage();
    }
}
