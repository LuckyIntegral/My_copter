package my.copter.persistence.sql.repository.product;

import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.repository.BaseRepository;
import my.copter.persistence.sql.type.BrandType;
import my.copter.persistence.sql.type.CategoryType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CopterRepository extends BaseRepository<Copter> {
    Page<Copter> findAllByBrand(BrandType brandType, Pageable pageable);
    Page<Copter> findAllByCategoryType(CategoryType categoryType, Pageable pageable);
}
