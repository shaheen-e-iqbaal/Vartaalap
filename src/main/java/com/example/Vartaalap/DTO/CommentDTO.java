package com.example.Vartaalap.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO userDTO;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleDTO articleDTO;

    @Column(name = "parent_comment_id")
    private long parentCommentId;

    @Column(name = "createdon")
    private LocalDateTime createdOn;


    @PrePersist
    void preInsert(){
        if(this.createdOn == null)
        this.createdOn = LocalDateTime.now();
    }

}
