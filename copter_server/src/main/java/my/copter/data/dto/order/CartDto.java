package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private Date created;
    private List<DroneOrderDto> drones;
    private Long price;
}
