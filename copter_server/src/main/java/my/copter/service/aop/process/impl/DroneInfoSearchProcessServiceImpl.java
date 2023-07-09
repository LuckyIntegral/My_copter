package my.copter.service.aop.process.impl;

import lombok.AllArgsConstructor;
import my.copter.exception.UserNotFoundException;
import my.copter.persistence.elasticsearch.document.DroneSearchIndex;
import my.copter.persistence.sql.entity.user.Customer;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.user.CustomerRepository;
import my.copter.service.aop.process.DroneInfoSearchProcessService;

import my.copter.service.statistic.DronePDPSearchService;
import my.copter.util.SecurityUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DroneInfoSearchProcessServiceImpl implements DroneInfoSearchProcessService {

    private final DronePDPSearchService service;
    private final CustomerRepository customerRepository;

    @Override
    public void saveRequestToDronePdp(Long id) {
        DroneSearchIndex index = new DroneSearchIndex();
        index.setDroneId(id);
        index.setCreated(new Date());
        index.setUsername(getFullName());
        service.create(index);
    }

    private String getFullName() {
//        Optional<Customer> customer = customerRepository
//                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        String username;
//        if (customer.isPresent()) {
//            username = customer.get().getFullName();
//        } else {
//            username = "unanimous";
//        }
//        System.out.println("username = " + username);
//        return username;

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = principal instanceof User ? ((User) principal).getFullName() : "unanimous";
//
//        if (principal instanceof User) {
//            username = ((User) principal).getUsername();
//        } else {
//            username = "unanimous";
//        }
//        System.out.println("username = " + username);
//
//
//        return username;
        return "unanimous";
    }
}
