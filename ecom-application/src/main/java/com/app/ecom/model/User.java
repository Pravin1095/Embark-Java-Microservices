package com.app.ecom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor //NoArgs constructor is mandatory because hibernate instantiate User objects by calling a no argument constructor

@Entity(name="user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role = UserRole.CUSTOMER; //default we are assigning customer
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) //This annotation tells that whenever an user field is getting updated or deleted the relevant address field also should be updated or deleted
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp //creates date automatically when a user is created
    private LocalDateTime createdAt;

    @UpdateTimestamp //updates date automatically when user is created
    private LocalDateTime updatedAt;

}
