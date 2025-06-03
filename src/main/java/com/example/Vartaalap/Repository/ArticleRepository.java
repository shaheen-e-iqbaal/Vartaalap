package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByArticleId(int articleId);

    List<Article> findByAuthorId(int authorId);

    List<Article> findByPublishedDate(LocalDateTime publishedDate);

    List<Article> findByTitleIgnoreCase(String title);

    List<Article> findByAuthorIdAndPremiumRequired(int authorId, boolean premiumRequired);
}
