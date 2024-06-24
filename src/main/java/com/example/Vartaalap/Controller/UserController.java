package com.example.Vartaalap.Controller;

import com.example.Vartaalap.DTO.UserDTO;
import com.example.Vartaalap.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/user")
public class UserController {

    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
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

    @PutMapping(path = "updateuser")
    public UserDTO update(@RequestParam int userId, @RequestBody UserDTO userDTO){
        userDTO.setUserId(userId);
        return userService.save(userDTO);
    }


}
