package com.microservice.app.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Author: oduorfrancis134@gmail.com
 * Date : 13/06/2022
 * This is a user entity class that defines the attributes of a user (email, first name, last name, credit)
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class User {
    @Id
    @NotNull
    private String email;

    private String firstName;
    private String lastName;
    private Double credit;

}
