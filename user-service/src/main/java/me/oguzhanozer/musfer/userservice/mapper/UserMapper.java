package me.oguzhanozer.musfer.userservice.mapper;

import org.mapstruct.Mapper;

import me.oguzhanozer.musfer.userservice.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userservice.dto.response.UserResponse;
import me.oguzhanozer.musfer.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest userCreateRequest);

    UserResponse toResponse(User user);
}
