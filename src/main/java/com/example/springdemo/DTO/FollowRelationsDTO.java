package com.example.springdemo.DTO;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Table(name = "followrelationsdto")
public class FollowRelationsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "who_is_following", nullable = false)
    private int whoIsFollowing;
    @Column(name = "who_is_being_followed", nullable = false)
    private int whoIsBeingFollowed;

    public FollowRelationsDTO(){}

    public String toString(){
        return id + " " + whoIsFollowing + " " + whoIsBeingFollowed;
    }
}
