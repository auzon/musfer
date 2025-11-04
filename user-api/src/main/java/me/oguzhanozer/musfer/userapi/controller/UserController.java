package me.oguzhanozer.musfer.userapi.controller;

import jakarta.validation.Valid;
import me.oguzhanozer.musfer.userapi.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userapi.dto.response.UserResponse;
import me.oguzhanozer.musfer.userapi.entity.User;
import me.oguzhanozer.musfer.userapi.mapper.UserMapper;
import me.oguzhanozer.musfer.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        User createdUser = userService.createUser(userMapper.toEntity(userCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toResponse(user));
    }
}
