package com.example.springdemo.Service;

import com.example.springdemo.DTO.ArticleDTO;
import com.example.springdemo.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    ArticleRepository articleRepository;
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    //Method to save the article
    public ArticleDTO save (ArticleDTO articleDTO){
        return articleRepository.save(articleDTO);
    }

    //Method to get Article by articleId
    public ArticleDTO findByAtricleId(int articleId){
        return articleRepository.findByAtricleId(articleId);
    }

    //Method to get Article by authorId
    public List<ArticleDTO> findByAuthorId(int authorId){
        return articleRepository.findByAuthorId(authorId);
    }

    //Method to get Article by published Date;
    public  List<ArticleDTO> findByPublishedDate(Date date){
        return articleRepository.findByPublishedDate(date);
    }

    //Method to get Articles by title;
    public List<ArticleDTO> findByTitle(String title){
        return articleRepository.findByTitle(title.toLowerCase());
    }

    //Method to get Articles by premiumrequired and authorId
    public List<ArticleDTO> findByAuthorIdAndPremiumRequired(int authorId, boolean premiumRequired){
        return articleRepository.findByAuthorIdAndPremiumRequired(authorId, premiumRequired);
    }
}
