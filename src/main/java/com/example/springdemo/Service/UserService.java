package com.example.springdemo.Service;

import com.example.springdemo.DTO.UserDTO;
import com.example.springdemo.Repository.UserRepository;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO save (UserDTO userDTO){
        if(findByEmailIdOrPassword(userDTO.getEmailId(),userDTO.getPassword()) == true)return null;
        return userRepository.save(userDTO);
    }

    public UserDTO findUserById(int userId){
        List<UserDTO> resp = userRepository.findByUserId(userId);
        return resp.size() == 1 ? resp.get(0) : null;
    }

    public boolean findByEmailIdAndPassword(String email, String password){
        return userRepository.findByEmailIdAndPassword(email,password).size() > 0 ? true : false;
    }

    public boolean findByEmailIdOrPassword(String email, String password){
        return userRepository.findByEmailIdOrPassword(email,password).size() > 0 ? true : false;
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll();
    }

}
