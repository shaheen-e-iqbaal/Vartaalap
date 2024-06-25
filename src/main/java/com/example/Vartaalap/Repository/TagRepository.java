package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.ArticleDTO;
import com.example.Vartaalap.DTO.TagDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagDTO, Long> {

    //Method to find article by Tag;
    List<TagDTO> findByTag(String tag);
}
