package me.oguzhanozer.musfer.userservice.util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import me.oguzhanozer.musfer.userservice.enums.UserStatus;

import java.time.Instant;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class TestUser {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String email = "john@email.com";
    @Builder.Default
    private String username = "john";
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;
    @Builder.Default
    private Instant createdAt = Instant.now();
    @Builder.Default
    private Instant updatedAt = Instant.now();

    public static final TestUser WITH_VALID_VALUES = TestUser.builder().build();
    public static final TestUser WITH_LONG_USERNAME = TestUser.builder().username("j".repeat(31)).build();
    public static final TestUser WITH_SHORT_USERNAME = TestUser.builder().username("j").build();
    public static final TestUser WITH_BLANK_USERNAME_EMAIL = TestUser.builder().email("").username("").build();
    public static final TestUser WITH_INVALID_PATTERN_USERNAME_EMAIL = TestUser.builder().email("john@")
            .username("john*").build();
}
