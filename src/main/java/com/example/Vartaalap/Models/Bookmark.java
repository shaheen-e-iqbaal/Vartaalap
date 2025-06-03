package com.example.Vartaalap.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference(value = "user-bookmarks")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference(value = "article-bookmarks")
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

//    @Override
//    public int hashCode(){
//        return Objects.hash(this.getArticleDTO().hashCode());
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        if (this.getArticle().getArticleId() == bookmark.getArticle().getArticleId() &&
                this.getUser().getUserId() == bookmark.getUser().getUserId()) return true;
        return false;
    }



    public Bookmark(){}
}
