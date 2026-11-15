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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        return new ResponseEntity<>(service.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id)
    {
        return service.fetchUser(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());


    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){

        service.addUser(user);
        return new ResponseEntity<>("User Added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updateduser)
    {
        if(service.updateUser(id, updateduser)){
            return new ResponseEntity<>("User updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User not found. Please add the user",HttpStatus.NOT_FOUND);
        }


    }
}
