package my.copter.util;

import my.copter.data.datatable.DataTableRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public final class PersistenceUtil {

    private PersistenceUtil() { }

    public static Pageable generatePageableByDataTableRequest(DataTableRequest request) {
        return PageRequest.of(request.getPage(), request.getSize(), Sort.by(request.getOrder()).ascending());
    }
}
