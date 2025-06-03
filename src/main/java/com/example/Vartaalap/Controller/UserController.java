package com.example.Vartaalap.Controller;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register with password encryption
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        if(savedUser == null)return ResponseEntity.badRequest().body("User already exists with this email.");
        return ResponseEntity.status(201).body(savedUser);
    }

    // ✅ Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found with ID: " + userId);
        }
        return ResponseEntity.ok(user);
    }

    // ❌ Removed manual login endpoint — Spring Security handles login

    // ✅ Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // ✅ Get liked articles by user
    @GetMapping("/{userId}/likes")
    public Set<Article> getUserLikes(@PathVariable int userId) {
        return userService.getLike(userId);
    }
}
