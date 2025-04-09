package com.example.Vartaalap.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bookmarkeddto")
public class BookmarkedDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDTO userDTO;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleDTO articleDTO;


    public BookmarkedDTO(){}
}
