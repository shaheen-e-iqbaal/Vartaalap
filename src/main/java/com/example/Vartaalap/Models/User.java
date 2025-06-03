package com.example.Vartaalap.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bio", nullable = false)
    private String Bio;

    @Column(name = "issubscribed", nullable = false)
    private boolean isSubscribed;

    @Column(name = "role", nullable = false)
    private String role = "USER";

    @JsonManagedReference(value = "user-likes")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @JsonManagedReference(value = "user-bookmarks")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @JsonManagedReference(value = "user-followed")
    @OneToMany(mappedBy = "whoIsBeingFollowed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowRelations> followers = new ArrayList<>();

    @JsonManagedReference(value = "user-following")
    @OneToMany(mappedBy = "whoIsFollowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowRelations> following = new ArrayList<>();

    @JsonManagedReference(value = "user-comments")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public User() {}

    // Spring Security - UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // equals and hashCode
    @Override
    public int hashCode() {
        return this.getEmailId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.getEmailId().equals(user.getEmailId());
    }

    @Override
    public String toString() {
        return userId + " " + first_name + " " + last_name + " " + emailId + " " + password + " " + Bio + " " + isSubscribed;
    }
}
