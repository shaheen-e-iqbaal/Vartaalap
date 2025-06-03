package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Likes;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Repository.LikesRepository;
import com.example.Vartaalap.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, LikesRepository likesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        if (userRepository.findByEmailId(user.getEmailId()).isPresent()) {
            return null; // Email already registered
        }

        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_" + user.getRole());
        return userRepository.save(user);
    }

    public User findUserById(int userId) {
        return userRepository.findByUserId(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Set<Article> getLike(int userId) {
        Set<Article> likedArticles = new HashSet<>();
        List<Likes> likesList = likesRepository.findByUserUserId(userId);
        for (Likes like : likesList) {
            likedArticles.add(like.getArticle());
        }
        return likedArticles;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailId(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorities);
    }

    public Optional<User> findByEmailId(String email) {
        return userRepository.findByEmailId(email);
    }
}
