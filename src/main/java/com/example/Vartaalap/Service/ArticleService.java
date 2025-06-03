package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Comment;
import com.example.Vartaalap.Models.Tag;
import com.example.Vartaalap.Repository.ArticleRepository;
import com.example.Vartaalap.Repository.LikesRepository;
import com.example.Vartaalap.Repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ArticleService {

    ArticleRepository articleRepository;
    BookmarkedService bookmarkedService;
    LikesRepository likesRepository;
    TagRepository tagRepository;
    CommentService commentService;

    public ArticleService(ArticleRepository articleRepository, BookmarkedService bookmarkedService,
                          LikesRepository likesRepository, TagRepository tagRepository, CommentService commentService) {
        this.bookmarkedService = bookmarkedService;
        this.articleRepository = articleRepository;
        this.likesRepository = likesRepository;
        this.tagRepository = tagRepository;
        this.commentService = commentService;
    }

    // Save or update an article
    public Article save(Article article) {
        if (article.getTags() != null) {
            for (Tag tag : article.getTags()) {
                tag.setArticle(article);
            }
        }
        return articleRepository.save(article);
    }

    public Optional<Article> findByArticleId(int articleId) {
        return articleRepository.findByArticleId(articleId);
    }

    public List<Article> findByAuthorId(int authorId) {
        return articleRepository.findByAuthorId(authorId);
    }

    public List<Article> findByPublishedDate(LocalDateTime date) {
        return articleRepository.findByPublishedDate(date);
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitleIgnoreCase(title.toLowerCase());
    }

    public List<Article> findByAuthorIdAndPremiumRequired(int authorId, boolean premiumRequired) {
        return articleRepository.findByAuthorIdAndPremiumRequired(authorId, premiumRequired);
    }

    public void deleteByArticleId(int articleId) {
        articleRepository.deleteById(articleId);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Modifying
    @Transactional
    public Optional<Article> updateArticleTags(int articleId, Set<Tag> newTags) {
        Article article = articleRepository.findByArticleId(articleId).orElse(null);
        if(article == null)return Optional.empty();

        article.getTags().clear();

        for (Tag tag : newTags) {
            tag.setArticle(article);
        }

        article.getTags().addAll(newTags);
        return Optional.of(articleRepository.save(article));
    }



    public List<Article> findByTag(String tag) {
        List<Article> articles = new ArrayList<>();
        List<Tag> tags = tagRepository.findByTag(tag);

        for (Tag tagDTO : tags) {
            articles.add(tagDTO.getArticle());
        }
        return articles;
    }

    public List<Comment> getComments(long articleId){
        return commentService.findByArticleId(articleId);
    }

}
