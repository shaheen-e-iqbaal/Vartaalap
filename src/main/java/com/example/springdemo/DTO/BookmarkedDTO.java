package com.example.springdemo.DTO;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bookmarkeddto")
public class BookmarkedDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "article_id", nullable = false)
    private int articleId;

    public BookmarkedDTO(){}
}
