package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.LikesDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface LikesRepository extends JpaRepository<LikesDTO, Long> {

    @Modifying
    @Transactional
    public Long deleteByUserId(int userId);

    public List<LikesRepository> findByUserId(int userId);
}
