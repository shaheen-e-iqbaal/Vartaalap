package com.example.Vartaalap.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follow_relations_dto")
public class FollowRelationsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "who_is_following")
    private UserDTO whoIsFollowing;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "who_is_being_followed")
    private UserDTO whoIsBeingFollowed;

    public FollowRelationsDTO(){}

    public String toString(){
        return id + " " + whoIsFollowing + " " + whoIsBeingFollowed;
    }
}
