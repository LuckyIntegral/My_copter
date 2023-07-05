package my.copter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("my.copter.persistence.sql.repository")
public class JpaConfig { }