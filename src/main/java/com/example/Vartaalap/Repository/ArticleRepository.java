package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.ArticleDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ArticleRepository extends JpaRepository<ArticleDTO, Long> {

    //Method to save Article
    public ArticleDTO save (ArticleDTO articleDTO);

    //Method to get Article by articleId
    public ArticleDTO findByArticleId(int articleId);

    //Method to get Article by authorId
    public List<ArticleDTO> findByAuthorId(int authorId);

    //Method to get Article by published Date;
    public  List<ArticleDTO> findByPublishedDate(Date date);

    //Method to get Articles by title;
    @Query("SELECT a FROM ArticleDTO a WHERE LOWER(a.title) = :title")
    public List<ArticleDTO> findByTitle(String title);

    //Method to get Articles by premiumrequired and authorId
    public List<ArticleDTO> findByAuthorIdAndPremiumRequired(int authorId, boolean premiumRequired);

    //Method to delete Article by articleId
    @Modifying
    @Transactional
    public Long deleteByArticleId(int articleId);

    //Method to get All Articles
    List<ArticleDTO> findAll();



}
