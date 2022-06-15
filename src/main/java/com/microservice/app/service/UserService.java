package com.microservice.app.service;


import com.microservice.app.dao.UserRepository;
import com.microservice.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: oduorfrancis134@gmail.com
 * Date: 13/06/2022
 * This is a service provider class to interface between data layer and the controller
 */

@Service
public class UserService {
    /**
     * Injecting the user repository dependency in to this class
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Add a user to the system
     */

    public User saveUser(User user, String email) throws EmailAlreadyTakenException {

        //Check if the email address is already taken
        Optional<User> testEmail = userRepository.findById(email);

        if (testEmail == null){
            return userRepository.save(user);
        }else {
            throw new EmailAlreadyTakenException("Email address is already taken, use another one");
        }
    }

    /**
     * Get a List of all users sorted in ascending order based on their first name
     */
    public List<User> getAllSystemUsers (){
        return userRepository.findAll();
    }

    /**
     * Find a user by email address
     * @return the user with the email address
     */

    public Optional<User> getUserByEmailAddress(String email) throws UserNotFoundException {

        //Check if the email address exists
        Optional<User> testEmail = userRepository.findById(email);

        if (testEmail.isPresent()) { //if the email exists delete user
            return userRepository.findById(email);
        }else {
            throw  new UserNotFoundException("User does not exist");
        }

    }

    /**
     * Delete a user by id
     */

    public void deleteUser(String email) throws UserNotFoundException {

        //Check if the email address exists
        Optional<User> testEmail = userRepository.findById(email);

        if (testEmail.isPresent()){ //if the email exists delete user
            userRepository.deleteById(email);
        }else {
            throw new UserNotFoundException("User does not exist");
        }

    }

    /**
     * Update a user
     * @return
     */
    public User updateUser(User user, String email) throws UserNotFoundException {

        Optional<User> testEmail = userRepository.findById(email);

        if (testEmail.isPresent()) {
            //Create a new user instance
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setCredit(user.getCredit());
            return userRepository.save(newUser);
        } else {throw new UserNotFoundException("User not available for update");}
    }



}
