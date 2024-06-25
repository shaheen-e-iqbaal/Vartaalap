package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.ArticleDTO;
import com.example.Vartaalap.DTO.LikesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<LikesDTO, Long> {

    //Method to get articled liked by User
    List<LikesDTO> findByUserId(int userId);
}
