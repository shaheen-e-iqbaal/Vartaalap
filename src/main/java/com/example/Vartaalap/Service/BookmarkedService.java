package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Bookmark;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Repository.BookmarkedRepository;
import com.example.Vartaalap.Repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkedService {

    private final BookmarkedRepository bookmarkedRepository;
    private final UserService userService;
    private final ArticleRepository articleRepository;

    public BookmarkedService(BookmarkedRepository bookmarkedRepository, UserService userService,
                             ArticleRepository articleRepository) {
        this.bookmarkedRepository = bookmarkedRepository;
        this.userService = userService;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public String addBookmark(int userId, int articleId) {
        if (bookmarkedRepository.findByUserUserIdAndArticleArticleId(userId, articleId) != null)
            return "Already bookmarked";

        User user = userService.findUserById(userId);
        Article article = articleRepository.findByArticleId(articleId).get();

        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setArticle(article);
        bookmarkedRepository.save(bookmark);
        return "Bookmark added";
    }

    @Transactional
    public String removeBookmark(int userId, int articleId) {
        Bookmark bookmark = bookmarkedRepository.findByUserUserIdAndArticleArticleId(userId, articleId).get();
        if (bookmark != null) {
            bookmarkedRepository.delete(bookmark);
            return "Bookmark removed";
        }
        return "Bookmark not found";
    }

    // Get all bookmarked articles (DTOs) for a user
    public List<Article> findBookmarkedArticlesByUserId(int userId) {
        List<Bookmark> bookmarks = bookmarkedRepository.findByUserUserId(userId);
        return bookmarks.stream()
                .map(Bookmark::getArticle)
                .collect(Collectors.toList());
    }

    // Check if an article is already bookmarked by user
    public boolean isAlreadyBookmarked(int userId, int articleId) {
        return bookmarkedRepository.findByUserUserIdAndArticleArticleId(userId, articleId).isPresent();
    }


    // Unbookmark an article
    public String unBookmark(int userId, int articleId) {
        if (isAlreadyBookmarked(userId, articleId)) {
            bookmarkedRepository.deleteByUserUserIdAndArticleArticleId(userId, articleId);
            return "Removed from bookmarked successfully.";
        } else {
            return "No such bookmarked article for this user.";
        }
    }


}
