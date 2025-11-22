package me.oguzhanozer.musfer.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.userservice.dto.request.UserCreateRequest;
import me.oguzhanozer.musfer.userservice.dto.response.UserResponse;
import me.oguzhanozer.musfer.userservice.entity.User;
import me.oguzhanozer.musfer.userservice.mapper.UserMapper;
import me.oguzhanozer.musfer.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @GetMapping("/current-db")
    public String currentDatabase() {
        return jdbcTemplate.queryForObject("SELECT current_database()", String.class);
    }
}
