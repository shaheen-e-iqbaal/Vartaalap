package com.example.Vartaalap.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "tag", nullable = false)
    private String tag;

    @JsonBackReference(value = "article-tags")
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;


    public Tag() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return this.getArticle().getArticleId() == tag.getArticle().getArticleId() && this.getTag() == tag.getTag();
    }
}
