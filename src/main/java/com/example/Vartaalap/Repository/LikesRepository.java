package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    //Method to get articled liked by User
    Likes findByUserUserIdAndArticleArticleId(int userId, int articleId);

    List<Likes> findByUserUserId(int userId);
}
