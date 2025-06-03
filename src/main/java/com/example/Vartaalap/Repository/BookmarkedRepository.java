package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkedRepository extends JpaRepository<Bookmark, Integer> {

    // Get all bookmarks for a user
    List<Bookmark> findByUserUserId(int userId);

    // Check if already bookmarked
    Optional<Bookmark> findByUserUserIdAndArticleArticleId(int userId, int articleId);

    // Delete a bookmark
    void deleteByUserUserIdAndArticleArticleId(int userId, int articleId);

}
