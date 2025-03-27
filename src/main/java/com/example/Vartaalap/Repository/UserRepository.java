package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDTO, Long> {

    //@Query("select userId from UserDTO where emailId = :emailId and password = :password")
    public List<UserDTO> findByEmailIdAndPassword(String emailId, String password);

    public List<UserDTO> findByEmailIdOrPassword(String emailId, String password);

    //@Query(value = "select u from UserDTO u where u.userId = :userId")
    public List<UserDTO> findByUserId(int userId);

    public List<UserDTO> findAll();

}
