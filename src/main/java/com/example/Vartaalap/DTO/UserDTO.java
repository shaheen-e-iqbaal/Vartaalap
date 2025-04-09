package com.example.Vartaalap.DTO;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String  last_name;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bio", nullable = false)
    private String Bio;

    @Column(name = "issubscribed", nullable = false)
    private boolean isSubscribed;

    @JsonManagedReference
    @OneToMany(mappedBy = "userDTO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikesDTO> likes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "userDTO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookmarkedDTO> bookmarks = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "whoIsBeingFollowed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowRelationsDTO> followers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "whoIsFollowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowRelationsDTO> following = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "userDTO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentDTO> comments = new ArrayList<>();


    public UserDTO(){}

    public String toString(){
        return userId + " " + first_name + " " + last_name + " " + emailId + " " + password + " " + Bio + " " + isSubscribed;
    }

}
