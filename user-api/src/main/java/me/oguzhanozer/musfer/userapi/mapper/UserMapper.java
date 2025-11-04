package me.oguzhanozer.musfer.userapi.mapper;

import me.oguzhanozer.musfer.userapi.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userapi.dto.response.UserResponse;
import me.oguzhanozer.musfer.userapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest userCreateRequest);
    UserResponse toResponse(User user);
}
