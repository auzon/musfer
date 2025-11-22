package me.oguzhanozer.musfer.userservice.unit;

import me.oguzhanozer.musfer.userservice.entity.User;
import me.oguzhanozer.musfer.userservice.exception.EmailAlreadyExistsException;
import me.oguzhanozer.musfer.userservice.repository.UserRepository;
import me.oguzhanozer.musfer.userservice.service.UserService;
import me.oguzhanozer.musfer.userservice.util.TestUser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static me.oguzhanozer.musfer.userservice.util.TestUserHelper.persistentEntityOf;
import static me.oguzhanozer.musfer.userservice.util.TestUserHelper.transientEntityOf;
import static me.oguzhanozer.musfer.userservice.util.UserMatchers.argMatches;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldThrowEmailAlreadyExistsException_whenEmailAlreadyExists() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        when(userRepository.existsByEmail(validUser.getEmail())).thenReturn(true);
        // act
        Throwable thrown = catchThrowable(() -> userService.createUser(transientEntityOf(validUser)));
        // assert
        assertThat(thrown).isInstanceOf(EmailAlreadyExistsException.class);
    }

    @Test
    void createUser_shouldReturnUser_whenUserCreated() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        // User mockSavedUser = persistentEntityOf(validUser);
        when(userRepository.existsByEmail(validUser.getEmail())).thenReturn(false);
        when(userRepository.save(argMatches(transientEntityOf(validUser)))).thenReturn(persistentEntityOf(validUser));
        // act
        User createdUser = userService.createUser(transientEntityOf(validUser));
        // assert
        assertThat(createdUser)
                .usingRecursiveComparison()
                .isEqualTo(persistentEntityOf(validUser));
    }

}
