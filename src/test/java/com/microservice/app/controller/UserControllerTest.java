package com.microservice.app.controller;

import com.microservice.app.dao.UserRepository;
import com.microservice.app.model.User;
import com.microservice.app.service.EmailAlreadyTakenException;
import com.microservice.app.service.UserNotFoundException;
import com.microservice.app.service.UserService;
import org.hamcrest.Matchers;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    User user1 = new User("oduorfrancis134@gmail.com", "Xavier", 1247.50, "Kisumu", "345", "+254778989909");
    User user2 = new User("otienochris98@gmail.com", "chrisothis", 450.98, "Nairobi", "315", "+25471298909");

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
    @DisplayName("Return User with the asserted id (email)")
    void findUserById() throws UserNotFoundException, Exception {
        Mockito.when(userService.getUserByEmailAddress(user1.getEmail())).thenReturn(Optional.ofNullable(user1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/oduorfrancis134@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userName").value("Xavier"));

    }
    @Test
    @DisplayName("User with the given email address should be deleted")
    void deleteUserByEmailAddress_success() throws UserNotFoundException, Exception {
        Mockito.when(userService.getUserByEmailAddress(user1.getEmail())).thenReturn(Optional.of(user1));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/delete-user/oduorfrancis134@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", nullValue()));


    }


    @Test
    @DisplayName("Save a user")
    void saveUser() throws Exception {
        User newUser = new User();

        newUser.setEmail("rivianstacia@gmail.com");
        newUser.setUserName("Rivian");
        newUser.setCredit(269.78);
        newUser.setCity("Kisumu");
        newUser.setZip("314");
        newUser.setPhoneNumber("+254798065467");

        Mockito.when(userService.saveUser(newUser)).thenReturn(newUser);
    }
    @Test
    @DisplayName("Update a User")
    void updateUser() throws UserNotFoundException, Exception {
        //create a user
        User user3 = new User("ohtisochris98@gmail.com", "Christopher", 10890.00,"Mombasa", "216", "0714567895");

        Mockito.when(userService.getUserByEmailAddress(user3.getEmail())).thenReturn(Optional.of(user3));

        user3.setUserName("Ericko");

        userService.saveUser(user3);

    }
}