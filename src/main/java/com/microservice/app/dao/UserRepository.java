package com.microservice.app.dao;

import com.microservice.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: oduorfrancis134@gmail.com
 * Date: 13/06/2022
 * An interface that supports the CRUD operations on user records
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
