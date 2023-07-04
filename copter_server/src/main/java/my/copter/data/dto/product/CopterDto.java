package my.copter.data.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
}
