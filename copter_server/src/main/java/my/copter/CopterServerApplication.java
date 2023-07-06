package my.copter;

import my.copter.config.security.dto.RegisterRequest;
import my.copter.config.security.service.AuthenticationService;
import my.copter.persistence.sql.repository.user.AdminRepository;
import my.copter.persistence.sql.type.RoleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CopterServerApplication {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
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