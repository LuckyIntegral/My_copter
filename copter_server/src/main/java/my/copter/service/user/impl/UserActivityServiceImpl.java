package my.copter.service.user.impl;

import lombok.AllArgsConstructor;

import my.copter.data.datatable.DataTableRequest;
import my.copter.exception.BadRequestException;
import my.copter.exception.UserNotFoundException;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.user.UserRepository;
import my.copter.service.user.UserActivityService;
import my.copter.util.PersistenceUtil;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static my.copter.util.ExceptionUtil.BAD_REQUEST_EXCEPTION;
import static my.copter.util.ExceptionUtil.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final UserRepository<User> userRepository;

    @Override
    @Transactional
    public Page<User> findAllUsers(DataTableRequest request) {
        return userRepository.findAll(PersistenceUtil.generatePageableByDataTableRequest(request));
    }

    @Override
    @Transactional
    public void disableUserById(Long id) {
        User user = getUserById(id);
        if (!user.getEnabled()) throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void enableUserById(Long id) {
        User user = getUserById(id);
        if (user.getEnabled()) throw new BadRequestException(BAD_REQUEST_EXCEPTION);
        user.setEnabled(true);
        userRepository.save(user);
    }

    private User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return user.get();
    }
}
