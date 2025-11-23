package me.oguzhanozer.musfer.userservice.unit;

import me.oguzhanozer.musfer.userservice.controller.UserController;
import me.oguzhanozer.musfer.userservice.exception.EmailAlreadyExistsException;
import me.oguzhanozer.musfer.userservice.exception.UserNotFoundException;
import me.oguzhanozer.musfer.userservice.mapper.UserMapper;
import me.oguzhanozer.musfer.userservice.service.UserService;
import me.oguzhanozer.musfer.userservice.util.TestUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static me.oguzhanozer.musfer.userservice.util.JsonHelper.*;
import static me.oguzhanozer.musfer.userservice.util.TestUserHelper.*;
import static me.oguzhanozer.musfer.userservice.util.UserMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvcTester mockMvcTester;

    private static final String USERS_ENDPOINT = "/users";

    @Test
    void createUser_shouldReturn201Created_whenUserCreated() throws Exception {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        when(userMapper.toEntity(argMatches(createRequestOf(validUser)))).thenReturn(transientEntityOf(validUser));
        when(userService.createUser(argMatches(transientEntityOf(validUser))))
                .thenReturn(persistentEntityOf(validUser));
        when(userMapper.toResponse(argMatches(persistentEntityOf(validUser)))).thenReturn(responseOf(validUser));
        // act
        mockMvc.perform(post(USERS_ENDPOINT).contentType(APPLICATION_JSON).content(createRequestJsonOf(validUser)))
                // assert
                .andExpectAll(
                        status().isCreated(),
                        content().json(responseJsonOf(validUser)));
    }

    @Test
    void createUser_shouldReturn409Conflict_whenEmailAlreadyExists() throws Exception {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        when(userMapper.toEntity(argMatches(createRequestOf(validUser)))).thenReturn(transientEntityOf(validUser));
        when(userService.createUser(argMatches(transientEntityOf(validUser))))
                .thenThrow(EmailAlreadyExistsException.class);
        // act
        mockMvc.perform(post(USERS_ENDPOINT).contentType(APPLICATION_JSON).content(createRequestJsonOf(validUser)))
                // assert
                .andExpectAll(
                        status().isConflict(),
                        keysOfJsonMatchesProblemDetailStandard());
    }

    @Test
    void createUser_shouldReturn400BadRequest_whenRequestBodyHasInvalidKeysOrValues() throws Exception {
        // act
        mockMvc.perform(post(USERS_ENDPOINT).contentType(APPLICATION_JSON).content(emptyJson()))
                // assert
                .andExpectAll(
                        status().isBadRequest(),
                        keysOfJsonMatchesProblemDetailStandard(),
                        jsonPath("$.errors").isNotEmpty());
    }

    @Test
    void getUser_shouldReturn200Ok_whenUserIsFound() throws Exception {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        when(userService.getUser(any())).thenReturn(persistentEntityOf(validUser));
        when(userMapper.toResponse(any())).thenReturn(responseOf(validUser));
        // act
        mockMvc.perform(get(USERS_ENDPOINT + "/" + validUser.getId()).contentType(APPLICATION_JSON))
                // assert
                .andExpectAll(
                        status().isOk(),
                        content().json(responseJsonOf(validUser)));

    }

    @Test
    void getUser_shouldReturn404NotFound_whenUserIsNotFound() throws Exception {
        // arrange
        TestUser validUser = TestUser.WITH_VALID_VALUES;
        when(userService.getUser(validUser.getId())).thenThrow(UserNotFoundException.class);
        // act
        mockMvc.perform(get(USERS_ENDPOINT + "/" + validUser.getId()).contentType(APPLICATION_JSON))
                // assert
                .andExpectAll(
                        status().isNotFound(),
                        keysOfJsonMatchesProblemDetailStandard());

    }

}
