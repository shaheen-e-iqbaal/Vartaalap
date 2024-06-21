package com.example.springdemo.Controller;

import com.example.springdemo.DTO.UserDTO;
import com.example.springdemo.Service.BookmarkedService;
import com.example.springdemo.Service.FollowRelationsService;
import com.example.springdemo.Service.UserService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    UserService userService;
    FollowRelationsService followRelationsService;
    public UserController(UserService userService, FollowRelationsService followRelationsService){
        this.userService = userService;
        this.followRelationsService = followRelationsService;
    }

    @PostMapping(path = "/saveUser")
    public Object saveUser(@RequestBody UserDTO userDTO) {
        UserDTO resp = userService.save(userDTO);
        if(resp == null)return "User Already exist with this emailId and/or Password";
        return resp;
    }

    @GetMapping(path = "/{userId}")
    public Object getUser(@PathVariable int userId){
        UserDTO resp =  userService.findUserById(userId);
        if(resp == null)return "No such user with this userId";
        return resp;
    }

    @PostMapping(path = "/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password){
        if(userService.findByEmailIdAndPassword(email,password))return "Succesfully loged in";
        return "Invalid Emailid or Password";
    }

    @GetMapping(path = "/getalluser")
    public List<UserDTO> getAllUser(){
        return userService.findAll();
    }


    //DEVELOP MEPPING TO UPDATE USER DETAILS AND TO DELETE USER


}
