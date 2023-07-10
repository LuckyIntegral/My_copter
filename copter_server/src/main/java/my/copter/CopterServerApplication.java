package my.copter;

import lombok.AllArgsConstructor;

import my.copter.config.security.dto.RegisterRequest;
import my.copter.config.security.service.AuthenticationService;
import my.copter.persistence.sql.repository.user.AdminRepository;
import my.copter.persistence.sql.type.RoleType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@AllArgsConstructor
public class CopterServerApplication {

    private AdminRepository adminRepository;
    private AuthenticationService service;

    public static void main(String[] args) {
        SpringApplication.run(CopterServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        String adminName = "admin";
        if (!adminRepository.existsByUsername(adminName)) {
            var admin = RegisterRequest.builder()
                    .username(adminName)
                    .firstName("Garry")
                    .lastName("Potter")
                    .password("12345678")
                    .roleType(RoleType.ADMIN)
                    .build();
            service.register(admin);
        }
    }
}