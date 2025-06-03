package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Likes;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Repository.ArticleRepository;
import com.example.Vartaalap.Repository.LikesRepository;
import com.example.Vartaalap.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public LikesService(LikesRepository likesRepository,
                       UserRepository userRepository,
                       ArticleRepository articleRepository) {
        this.likesRepository = likesRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public String addLike(int userId, int articleId) {
        Likes existingLike = likesRepository.findByUserUserIdAndArticleArticleId(userId, articleId);
        if (existingLike != null) {
            return "Already liked";
        }

        User user = userRepository.findByUserId(userId);
        Article article = articleRepository.findByArticleId(articleId).get();

        Likes like = new Likes();
        like.setUser(user);
        like.setArticle(article);
        likesRepository.save(like);

        return "Like added";
    }

    @Transactional
    public String removeLike(int userId, int articleId) {
        Likes like = likesRepository.findByUserUserIdAndArticleArticleId(userId, articleId);
        if (like != null) {
            likesRepository.delete(like);
            return "Like removed";
        }
        return "Like not found";
    }

}
