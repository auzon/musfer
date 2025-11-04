package me.oguzhanozer.musfer.userapi.util;

import me.oguzhanozer.musfer.userapi.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userapi.entity.User;

import java.util.Objects;


import static org.mockito.ArgumentMatchers.argThat;

public class UserMatchers {

    public static UserCreateRequest argMatches(UserCreateRequest other) {
        return argThat(u ->
                Objects.equals(u.email(), other.email()) &&
                Objects.equals(u.username(), other.username())
        );
    }

    public static User argMatches(User other) {
        return argThat(u ->
                Objects.equals(u.getId(), other.getId()) &&
                Objects.equals(u.getEmail(), other.getEmail()) &&
                Objects.equals(u.getUsername(), other.getUsername()) &&
                Objects.equals(u.getStatus(), other.getStatus()) &&
                Objects.equals(u.getCreatedAt(), other.getCreatedAt()) &&
                Objects.equals(u.getUpdatedAt(), other.getUpdatedAt())
        );
    }


}
