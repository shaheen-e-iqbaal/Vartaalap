package com.example.springdemo.Repository;

import com.example.springdemo.DTO.UserDTO;
import com.example.springdemo.DTO.ArticleDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    //@Query("select userId from UserDTO where emailId = :emailId and password = :password")
    public List<UserDTO> findByEmailIdAndPassword(String emailId, String password);

    public List<UserDTO> findByEmailIdOrPassword(String emailId, String password);

    //@Query(value = "select u from UserDTO u where u.userId = :userId")
    public List<UserDTO> findByUserId(int userId);

    public List<UserDTO> findAll();

}
