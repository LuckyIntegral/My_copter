package my.copter.data.datatable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequest {

    private int page;
    private int size;
    private String order;
}
