package my.copter.persistance.sql.repository.product;

import my.copter.persistance.sql.entity.product.Copter;
import my.copter.persistance.sql.repository.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CopterRepository extends BaseRepository<Copter> { }
