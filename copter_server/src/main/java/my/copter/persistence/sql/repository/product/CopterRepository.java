package my.copter.persistence.sql.repository.product;

import my.copter.persistence.sql.entity.product.Copter;
import my.copter.persistence.sql.repository.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CopterRepository extends BaseRepository<Copter> { }
