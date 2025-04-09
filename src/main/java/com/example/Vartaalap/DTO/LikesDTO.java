package com.example.Vartaalap.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO userDTO;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "article_number", nullable = false)
    private ArticleDTO articleDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikesDTO likesDTO = (LikesDTO) o;
        return this.getUserDTO().getUserId() == likesDTO.getUserDTO().getUserId() && this.getArticleDTO().getArticleId() == likesDTO.getArticleDTO().getArticleId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserDTO().getUserId());
    }

}
