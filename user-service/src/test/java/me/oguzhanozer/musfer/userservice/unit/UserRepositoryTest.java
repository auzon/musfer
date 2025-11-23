package me.oguzhanozer.musfer.userservice.unit;

import me.oguzhanozer.musfer.userservice.entity.User;
import me.oguzhanozer.musfer.userservice.repository.UserRepository;
import me.oguzhanozer.musfer.userservice.util.TestUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static me.oguzhanozer.musfer.userservice.util.TestUserHelper.transientEntityOf;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_shouldReturnEntity_whenEntityPersistAndFlushed() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        User persistentUserEntity = testEntityManager.persistAndFlush(transientEntityOf(validUser));
        // act
        User foundUserEntity = userRepository.findByEmail(validUser.getEmail()).orElse(null);
        // assert
        assertThat(foundUserEntity)
                .usingRecursiveComparison()
                .isEqualTo(persistentUserEntity);
    }

    @Test
    void findByEmail_shouldReturnEmptyOptional_whenEntityDoesNotExist() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        // act
        Optional<User> optionalFoundUser = userRepository.findByEmail(validUser.getEmail());
        // assert
        assertThat(optionalFoundUser).isEmpty();
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEntityPersistAndFlushed() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        testEntityManager.persistAndFlush(transientEntityOf(validUser));
        // act
        boolean existsByEmailResult = userRepository.existsByEmail(validUser.getEmail());
        // assert
        assertThat(existsByEmailResult).isTrue();
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEntityDoesNotExist() {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        // act
        boolean existsByEmailResult = userRepository.existsByEmail(validUser.getEmail());
        // assert
        assertThat(existsByEmailResult).isFalse();
    }
}
