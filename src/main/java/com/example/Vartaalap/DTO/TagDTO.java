package com.example.Vartaalap.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class TagDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tag")
    private String tag;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleDTO articleDTO;

    public TagDTO(){}
}
