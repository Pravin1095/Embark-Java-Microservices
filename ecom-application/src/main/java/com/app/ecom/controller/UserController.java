package com.app.ecom.controller;

import com.app.ecom.User;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;


    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(service.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id)
    {
        User user = service.fetchUser(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user){

        service.addUser(user);
        return new ResponseEntity<>("User Added successfully", HttpStatus.OK);
    }
}
