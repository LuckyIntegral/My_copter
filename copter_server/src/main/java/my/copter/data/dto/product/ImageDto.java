package my.copter.data.dto.product;

import lombok.Getter;
import lombok.Setter;
import my.copter.persistence.sql.entity.product.CopterImage;

@Getter
@Setter
public class ImageDto {
    private String imageUrl;
    private Boolean mainImage;

    public ImageDto(CopterImage image) {
        this.imageUrl = image.getImageUrl();
        this.mainImage = image.getMainImage();
    }
}
