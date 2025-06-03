package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(int userId);

    Optional<User> findByEmailId(String emailId); // ✅ Needed for Spring Security login
}
