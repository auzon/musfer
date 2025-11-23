package me.oguzhanozer.musfer.userservice.util;

import me.oguzhanozer.musfer.userservice.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userservice.dto.response.UserResponse;
import me.oguzhanozer.musfer.userservice.entity.User;

public class TestUserHelper {

    public static User persistentEntityOf(TestUser testUser) {
        return new User(
                testUser.getId(), testUser.getEmail(), testUser.getUsername(), testUser.getStatus(),
                testUser.getCreatedAt(), testUser.getUpdatedAt());
    }

    public static User transientEntityOf(TestUser testUser) {
        return new User(
                null, testUser.getEmail(), testUser.getUsername(), testUser.getStatus(),
                null, null);
    }

    public static UserResponse responseOf(TestUser testUser) {
        return new UserResponse(testUser.getId(), testUser.getEmail(), testUser.getUsername());
    }

    public static UserCreateRequest createRequestOf(TestUser testUser) {
        return new UserCreateRequest(testUser.getEmail(), testUser.getUsername());
    }

}
