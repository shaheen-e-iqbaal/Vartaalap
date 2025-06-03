package com.example.Vartaalap.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @JsonBackReference(value = "user-comments")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonBackReference(value = "article-comments")
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;


    @Column(name = "parent_comment_id")
    private long parentCommentId;

    @Column(name = "createdon")
    private LocalDateTime createdOn;

//    @Override
//    public int hashCode(){
//        return Objects.hash(this.getArticleDTO().getArticleId());
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        if (this.getArticle().getArticleId() == comment.getCommentId() &&
                comment.getUser().getUserId() == this.getUser().getUserId()) return true;
        return false;
    }


    @PrePersist
    void preInsert(){
        if(this.createdOn == null)
        this.createdOn = LocalDateTime.now();
    }

}
