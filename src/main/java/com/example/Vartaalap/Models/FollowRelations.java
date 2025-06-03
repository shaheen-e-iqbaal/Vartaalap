package com.example.Vartaalap.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follow_relations")
public class FollowRelations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference(value = "user-following")
    @ManyToOne
    @JoinColumn(name = "who_is_following")
    private User whoIsFollowing;

    @JsonBackReference(value = "user-followed")
    @ManyToOne
    @JoinColumn(name = "who_is_being_followed")
    private User whoIsBeingFollowed;


    public FollowRelations(){}

//    @Override
//    public int hashCode(){
//        return Objects.hash(this.getWhoIsFollowing().getUserId());
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowRelations followRelations = (FollowRelations) o;
        if (this.getWhoIsFollowing().getUserId() == followRelations.getWhoIsFollowing().getUserId() &&
                this.getWhoIsBeingFollowed().getUserId() == followRelations.getWhoIsBeingFollowed().getUserId())
            return true;
        return false;
    }



    public String toString(){
        return id + " " + whoIsFollowing + " " + whoIsBeingFollowed;
    }
}
