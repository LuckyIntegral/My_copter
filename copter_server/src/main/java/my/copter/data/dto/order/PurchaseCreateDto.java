package my.copter.data.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseCreateDto {
    private String contact;
    private String address;
    private Long cartId;
}
