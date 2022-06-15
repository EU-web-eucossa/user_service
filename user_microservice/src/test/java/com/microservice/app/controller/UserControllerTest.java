package com.microservice.app.controller;

import com.microservice.app.dao.UserRepository;
import com.microservice.app.model.User;
import com.microservice.app.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest annotation so that the dependencies that will be loaded when you run the test class are the ones directly affecting the controller class
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    //The userService interface is used in all of the API endpoints, so I've mocked it with @MockBean
    @MockBean
    UserService userService;

    /**
     * Create two user instances for testing purposes
     */
    User user1 = new User("oduorfrancis134@gmail.com", "Xavier", "Oduor", 1247.50);
    User user2 = new User("otienochris98@gmail.com", "Christopher", "Otieno", 500.95);

    @Test
    @DisplayName("All users should be displayed")
    void getAllUsers() throws Exception {
        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2));

        Mockito.when(userService.getAllSystemUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("User with the asserted id (email) should be displayed")
    void findUserById() {
    }

    @Test
    @DisplayName("User with the given email address should be deleted")
    void deleteUserByEmailAddress() {
    }

    @Test
    @DisplayName("User with the given email address should be updated")
    void updateUser() {
    }
}