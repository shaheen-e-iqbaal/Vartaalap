package com.example.Vartaalap.Service;

import com.example.Vartaalap.Models.Article;
import com.example.Vartaalap.Models.Comment;
import com.example.Vartaalap.Models.User;
import com.example.Vartaalap.Repository.ArticleRepository;
import com.example.Vartaalap.Repository.CommentRepository;
import com.example.Vartaalap.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    // Save new comment
    public Comment createComment(int userId, int articleId, String content, long parentCommentId) {
        User user = userRepository.findByUserId(userId);
        Article article = articleRepository.findByArticleId(articleId).get();


        Comment comment = new Comment();
        comment.setUser(user);
        comment.setArticle(article);
        comment.setContent(content);
        comment.setParentCommentId(parentCommentId);

        return commentRepository.save(comment);
    }

    // Update existing comment
    public Comment updateComment(long commentId, String newContent) {
        Comment comment = commentRepository.findByCommentId(commentId);
        if (comment != null) {
            comment.setContent(newContent);
            return commentRepository.save(comment);
        }
        return null;
    }

    // Soft delete a comment
    public Comment deleteComment(long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId);
        if (comment != null) {
            comment.setContent("[Deleted]");
            return commentRepository.save(comment);
        }
        return null;
    }

    // Get all child comments
    public List<Comment> getAllChildComments(long parentCommentId) {
        return commentRepository.findByParentCommentId(parentCommentId);
    }

    // Get comment by ID
    public Comment findByCommentId(long commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    // Get all comments by user
    public List<Comment> findByUserId(long userId) {
        return commentRepository.findByUserUserId((int) userId);
    }

    public List<Comment> findByArticleId(long articleId) {
        return commentRepository.findByArticleArticleIdAndParentCommentId(articleId, -1);
    }
}
