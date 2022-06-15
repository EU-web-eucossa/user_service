package com.microservice.app.database;


import com.microservice.app.dao.UserRepository;
import com.microservice.app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: oduorfrancis134@gmail.com
 *  Date: 13/06/2022
 * CommandLineRunner bean class to load data to the UserRepository created once the application context is loaded
 * Note: the application runs on H2 - database (in-memory database).
 * This runner will request a copy of the UserRepository I have just created.
 */

@Configuration
public class LoadDatabase {

    //initialize a logger to help log information to the console
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * @param userRepository : CommandLineRunner bean class to load data to the UserRepository created once the application context is loaded
     * @return : two instances of two users with all their attributes
     */
    @Bean
    CommandLineRunner initDatabase (UserRepository userRepository){
        return args -> {
            /**
             * Clear the in-memory database and add two new users
             */
            userRepository.deleteAll();
            log.info("Adding a user 1 to database" + "... " +  userRepository.save(new User("oduorfrancis134@gmail.com", "Xavier", "Oduor", 1247.50)));
            log.info("Adding a user 2 to database" +  "..." + userRepository.save(new User("otienochris98@gmail.com", "Christopher", "Otieno", 500.95)));
        };
    }



}
