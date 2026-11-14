package com.app.ecom.service;


import com.app.ecom.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    List<User> userList = new ArrayList<>();
    private Long id = 1L;

    public List<User> fetchAllUsers(){
        return userList;
    }

    public Optional<User> fetchUser(Long userId){
        return userList.stream().filter(u->u.getId()==userId).findFirst();

    }

    public List<User> addUser(User user){
        ;
        user.setId(id++);
        userList.add(user);
        return userList;
    }
}
