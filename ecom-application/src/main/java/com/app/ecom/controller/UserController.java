package com.app.ecom.controller;

import com.app.ecom.User;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;


    @GetMapping("/api/users")
    public List<User> getAllUsers(){
return service.fetchAllUsers();
    }

    @PostMapping("/api/users")
    public String createUser(@RequestBody User user){

        service.addUser(user);
        return "User Added successfully";
    }
}
