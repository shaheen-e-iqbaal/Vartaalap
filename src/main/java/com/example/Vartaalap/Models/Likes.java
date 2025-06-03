package com.example.Vartaalap.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference(value = "user-likes")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference(value = "article-likes")
    @ManyToOne
    @JoinColumn(name = "article_number", nullable = false)
    private Article article;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Likes likes = (Likes) o;
        return this.getUser().getUserId() == likes.getUser().getUserId() && this.getArticle().getArticleId() == likes.getArticle().getArticleId();
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(this.getUserDTO().getUserId());
//    }

}
