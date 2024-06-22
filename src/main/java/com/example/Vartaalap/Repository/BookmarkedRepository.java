package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.BookmarkedDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookmarkedRepository extends JpaRepository<BookmarkedDTO, Long> {

    @Query(value = "select articleId from BookmarkedDTO where userId = :userId")
    public List<Integer> AllBookmarkedArticlesByUserId(int userId);

    @Transactional
    @Modifying
    @Query(value = "insert into BookmarkedDTO (user_id, article_id) values (:userId, :articleId)", nativeQuery = true)
    public void BookmarkArticle(int userId, int articleId);

    @Query(value = "select userId from BookmarkedDTO where articleId = :articleId and userId = :userId")
    public List<Integer> isAlreadyBookmarked(int userId, int articleId);

    @Modifying
    @Transactional
    public void deleteByUserIdAndArticleId(int userId, int articleId);
}
