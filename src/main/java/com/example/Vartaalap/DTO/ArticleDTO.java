package com.example.Vartaalap.DTO;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "articledto")
public class ArticleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    @Column(name = "authorid", nullable = false)
    private int authorId;

    @Column(name = "publisheddate")
    private LocalDateTime publishedDate;

    @Column(name = "premiumrequired", nullable = false)
    private boolean premiumRequired;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "articleDTO", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    List<TagDTO> tags;

    @JsonManagedReference
    @OneToMany(mappedBy = "articleDTO", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LikesDTO> likes;


    public ArticleDTO(){}

    @PrePersist
    void preInsert() {
        if (this.publishedDate == null)
            this.publishedDate = LocalDateTime.now();
        this.setLikes(new ArrayList<>());
    }
}
