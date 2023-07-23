package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneQuantityDto {
    private Long droneId;
    private String name;
    private Integer quantity;
}
