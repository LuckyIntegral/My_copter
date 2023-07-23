package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseDto {
    private String username;
    private String contact;
    private String address;
    private Boolean active;
    private Long price;
    private Long cartId;
    private Long id;
    private List<DroneQuantityDto> drones;

    public PurchaseDto() {
        this.drones = new ArrayList<>();
    }
}
