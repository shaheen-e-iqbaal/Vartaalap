package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //Method to find article by Tag;
    List<Tag> findByTag(String tag);
}
