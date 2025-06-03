package com.example.Vartaalap.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@Table(name = "articles")
public class Article {
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

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @JsonManagedReference(value = "article-tags")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags;

    @JsonManagedReference(value = "article-likes")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Likes> likes = new HashSet<>();

    @JsonManagedReference(value = "article-bookmarks")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bookmark> bookmarks = new HashSet<>();

    @JsonManagedReference(value = "article-comments")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();


    public Article(){}

    @PrePersist
    void preInsert() {
        if (this.getPublishedDate() == null)
            this.setPublishedDate(LocalDateTime.now());
        this.setLikes(new HashSet<>());
    }

    @Override
    public int hashCode(){
        return this.getTitle().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        if (article.getAuthorId() == this.getAuthorId() && article.getTitle().hashCode() == this.getTitle().hashCode())
            return true;
        return false;
    }


}
