package com.app.ecom.service;


import com.app.ecom.dto.AddressDto;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
//    List<User> userList = new ArrayList<>();
//    private Long id = 1L;

    @Autowired
    private UserRepository userrepository;

    public List<UserResponse> fetchAllUsers(){

//        List<User> users = userrepository.findAll();
//        List<UserResponse> userresponses = users.stream().map((u)->mapUserResponse(u)).toList();
//        return userresponses;

        return userrepository.findAll().stream().map(this::mapUserResponse).collect(Collectors.toList());  //this::mapUserResponse means that every instance of User should be mapped to the userResponse.
    }

    public Optional<UserResponse> fetchUser(Long userId){
//        return userList.stream().filter(u->u.getId()==userId).findFirst();
return userrepository.findById(userId).map(this::mapUserResponse);
    }

    public void addUser(UserRequest userrequest){
//        user.setId(id++);
//        userList.add(user);
//        return userList;

        User user = new User();
                updateUserFromRequest(user, userrequest);
        userrepository.save(user);
    }



    public boolean updateUser(Long id , UserRequest updatedUser) {
       return userrepository.findById(id).map(existingUser->{
           updateUserFromRequest(existingUser,updatedUser);
       userrepository.save(existingUser);
       return true;
       }).orElse(false);
    }

    private void updateUserFromRequest(User user, UserRequest userrequest) {
        user.setEmail(userrequest.getEmail());
        user.setPhone(userrequest.getPhone());
        user.setFirstName(userrequest.getFirstName());
        user.setLastName(userrequest.getLastName());
        if(userrequest.getAddress()!=null){
            Address address =new Address();
            address.setCity(userrequest.getAddress().getCity());
            address.setZipcode(userrequest.getAddress().getZipcode());
            address.setCountry(userrequest.getAddress().getCountry());
            address.setStreet(userrequest.getAddress().getStreet());
            address.setState(userrequest.getAddress().getState());
            user.setAddress(address);
        }
    }

    private UserResponse mapUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        if(user.getAddress()!=null){
            AddressDto address = new AddressDto();
            address.setCity(user.getAddress().getCity());
            address.setZipcode(user.getAddress().getZipcode());
            address.setCountry(user.getAddress().getCountry());
            address.setStreet(user.getAddress().getStreet());
            address.setState(user.getAddress().getState());
            response.setAddress(address);
        }
        return response;
    }
}
