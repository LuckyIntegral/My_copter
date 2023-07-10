package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartEntryDto {
    private Long droneId;
    private Integer quantity;
}
