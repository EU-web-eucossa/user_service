package com.microservice.app.controller;

import com.microservice.app.model.User;
import com.microservice.app.service.EmailAlreadyTakenException;
import com.microservice.app.service.UserNotFoundException;
import com.microservice.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class
 */

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return all system users
     */
    @GetMapping("/users")
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {
//        return userService.getAllSystemUsers();

        //Write a hypertext driven output using Spring HATEOAS for more RESTful services
        List<User> allusers =  userService.getAllSystemUsers();



        User user = new User();
        EntityModel.of(user, linkTo(methodOn(UserController.class).findUserById(user.getEmail())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("/api/v1/users"));


        return  CollectionModel.of(allusers,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }
    /**
     * @return user by id (email)
     */
    @GetMapping("/users/{email}")
    public EntityModel<Optional<User>> findUserById(@PathVariable String email) throws UserNotFoundException {
        Optional<User> user = userService.getUserByEmailAddress(email);

        //Write a hypertext driven output using Spring HATEOAS for more RESTful services
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).findUserById(email)).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("/api/v1/users"));
    }

    /**
     * Save a user
     * @return
     */

    @PostMapping("/save-user/")
    public EntityModel<User> saveUser(@RequestBody User user) throws EmailAlreadyTakenException {

        User savedUser = userService.saveUser(user);

        return EntityModel.of(savedUser,
                linkTo(methodOn(UserController.class).saveUser(user)).withSelfRel());
    }

    /**
     * delete a given user
     */
    @DeleteMapping("/delete-user/{email}")
    public void deleteUserByEmailAddress(@PathVariable("email") String email) throws UserNotFoundException {
        userService.deleteUser(email);
    }

    /**
     * Update a user
     */
    @PutMapping("/update-user/{email}")
    public User updateUser(@RequestBody User user, @PathVariable("email") String email) throws UserNotFoundException {

        return userService.updateUser(user, email);
    }

}
