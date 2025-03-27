package com.example.Vartaalap.DTO;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String  last_name;

    @Column(name = "email_id", nullable = true)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "bio", nullable = false)
    private String Bio;

    @Column(name = "issubscribed", nullable = false)
    private boolean isSubscribed;


    public UserDTO(){}

    public String toString(){
        return userId + " " + first_name + " " + last_name + " " + emailId + " " + password + " " + Bio + " " + isSubscribed;
    }

}
