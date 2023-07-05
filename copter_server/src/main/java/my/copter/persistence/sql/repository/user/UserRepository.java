package my.copter.persistence.sql.repository.user;

import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository<U extends User> extends BaseRepository<U> {
    Optional<U> findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.enabled = true")
    Optional<U> findActiveByUsername(@Param("username") String username);
    boolean existsByUsername(String email);
}
