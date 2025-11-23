package me.oguzhanozer.musfer.userservice.unit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import me.oguzhanozer.musfer.userservice.dto.request.UserCreateRequest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class UserCreateRequestTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    @Test
    void validate_shouldReturnEmailMustNotBeBlankViolation_whenEmailIsBlank() {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest("", "john");
        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);
        // assert
        assertThat(violations).isNotEmpty()
                .filteredOn(v -> v.getPropertyPath().toString(), "email")
                .extracting(ConstraintViolation::getMessage)
                .contains("must not be blank");
    }

    @ParameterizedTest
    @ValueSource(strings = { "john", "john@", "@email.com", "john@@email.com", "john@email..com", "jo hn@email.com" })
    void validate_shouldReturnEmailMustBeWellFormedAddressViolation_whenEmailIsInvalid(String email) {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest(email, "john");

        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);

        // assert
        assertThat(violations).isNotEmpty()
                .filteredOn(v -> v.getPropertyPath().toString(), "email")
                .extracting(ConstraintViolation::getMessage)
                .contains("must be a well-formed email address");
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "john doe", "john*doe", "john__doe", "_john", "john_" })
    void validate_shouldReturnUsernameRegexViolation_whenUsernameIsNotUrlSafe(String username) {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest("john@email.com", username);
        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);
        // assert
        assertThat(violations).isNotEmpty()
                .filteredOn(v -> v.getPropertyPath().toString(), "username")
                .extracting(ConstraintViolation::getMessage)
                .contains("must match \"^[A-Za-z0-9]+(?:[-_.][A-Za-z0-9]+)*$\"");
    }

    @ParameterizedTest
    @ValueSource(strings = { "j", "john_username_but_its_longer_than_30" })
    void validate_shouldReturnUsernameSizeViolation_whenUsernameSizeIsOutOfBoundary(String username) {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest("john@email.com", username);
        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);
        // assert
        assertThat(violations).isNotEmpty()
                .filteredOn(v -> v.getPropertyPath().toString(), "username")
                .extracting(ConstraintViolation::getMessage)
                .contains("size must be between 3 and 30");
    }

    @Test
    void validate_shouldReturnUsernameMustNotBeBlankViolation_whenUsernameIsBlank() {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest("john@email.com", "");
        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);
        // assert
        assertThat(violations).isNotEmpty()
                .filteredOn(v -> v.getPropertyPath().toString(), "username")
                .extracting(ConstraintViolation::getMessage)
                .contains("must not be blank");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "joh", "john_doe_15char", "john_doe_username_exact_30char", // validSizedUsernames
            "john", "john_doe", "john-doe", "john.doe", "john123", "JohnDoe" // urlSafeUsernames
    })
    void validate_shouldNotReturnAnyViolation_whenUsernameIsValid(String username) {
        // arrange
        UserCreateRequest userCreateRequest = new UserCreateRequest("john@email.com", username);
        // act
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(userCreateRequest);
        // assert
        assertThat(violations).isEmpty();
    }

}
