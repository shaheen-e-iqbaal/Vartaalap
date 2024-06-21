package com.example.springdemo.DTO;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "articledto")
public class ArticleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int atricleId;

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


    public ArticleDTO(){}

    @PrePersist
    void preInsert() {
        if (this.publishedDate == null)
            this.publishedDate = LocalDateTime.now();
    }
}
