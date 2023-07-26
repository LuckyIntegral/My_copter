package my.copter.service.user;

import my.copter.exception.BadRequestException;
import my.copter.exception.UserNotFoundException;
import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.repository.user.UserRepository;
import my.copter.persistence.sql.type.RoleType;
import my.copter.service.user.impl.UserActivityServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static my.copter.util.EntityUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserActivityServiceTest {

    @InjectMocks
    private UserActivityServiceImpl userActivityService;

    @Mock
    private UserRepository<User> userRepository;

    @Test
    public void shouldDisableUserByIdWhenUserIsEnabled() {
        User user = getFilledUser(RoleType.CUSTOMER);
        user.setEnabled(true);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userActivityService.disableUserById(USER_ID);

        assertFalse(user.getEnabled());
        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenUserIsAlreadyDisabled() {
        User user = getFilledUser(RoleType.CUSTOMER);
        user.setId(USER_ID);
        user.setEnabled(false);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        assertThrows(BadRequestException.class, () -> userActivityService.disableUserById(USER_ID));
        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenInvalidUserIdIsProvided() {
        when(userRepository.findById(INVALID_USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userActivityService.disableUserById(INVALID_USER_ID));
        verify(userRepository, times(1)).findById(INVALID_USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void shouldEnableUserByIdWhenUserIsDisabled() {
        User user = getFilledUser(RoleType.CUSTOMER);
        user.setEnabled(false);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userActivityService.enableUserById(USER_ID);

        assertTrue(user.getEnabled());
        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldThrowBadRequestExceptionIfUserIsAlreadyEnabled() {
        User user = getFilledUser(RoleType.CUSTOMER);
        user.setId(USER_ID);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        assertThrows(BadRequestException.class, () -> userActivityService.enableUserById(USER_ID));
        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void shouldThrowUserNotFoundExceptionIfUserWithIdNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userActivityService.enableUserById(USER_ID));
        verify(userRepository, times(1)).findById(USER_ID);
        verify(userRepository, never()).save(any(User.class));
    }
}
