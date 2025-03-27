package com.example.Vartaalap.DTO;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Data
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "userid", nullable = false)
    private long userId;

    @Column(name = "articleid", nullable = false)
    private long articleId;

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
