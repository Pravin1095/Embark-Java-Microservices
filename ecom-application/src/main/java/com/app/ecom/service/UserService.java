package com.app.ecom.service;


import com.app.ecom.User;
import com.app.ecom.repository.UserRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
//    List<User> userList = new ArrayList<>();
//    private Long id = 1L;

    @Autowired
    private UserRepository userrepository;

    public List<User> fetchAllUsers(){
        return userrepository.findAll();
    }

    public Optional<User> fetchUser(Long userId){
//        return userList.stream().filter(u->u.getId()==userId).findFirst();
return userrepository.findById(userId);
    }

    public void addUser(User user){
//        user.setId(id++);
//        userList.add(user);
//        return userList;

        userrepository.save(user);
    }

    public boolean updateUser(Long id , User updatedUser) {
       return userrepository.findById(id).map(existingUser->{existingUser.setFirstName(updatedUser.getFirstName());
       existingUser.setLastName(updatedUser.getLastName());
       userrepository.save(existingUser);
       return true;
       }).orElse(false);
    }
}
