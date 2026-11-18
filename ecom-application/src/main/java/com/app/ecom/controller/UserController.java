package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        return new ResponseEntity<>(service.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id)
    {
        return service.fetchUser(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());


    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest user){

        service.addUser(user);
        return new ResponseEntity<>("User Added successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updateduser)
    {
        if(service.updateUser(id, updateduser)){
            return new ResponseEntity<>("User updated successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User not found. Please add the user",HttpStatus.NOT_FOUND);
        }


    }
}
