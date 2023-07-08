package my.copter.facade.plp;

import my.copter.data.datatable.DataTableRequest;
import my.copter.data.datatable.DataTableResponse;
import my.copter.data.dto.product.CopterPlpDto;

public interface DronePLPFacade {
    DataTableResponse<CopterPlpDto> findAll(DataTableRequest request, String brand, String category);
}
