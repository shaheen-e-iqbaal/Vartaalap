package com.example.Vartaalap.Controller;

import com.example.Vartaalap.Models.Comment;
import com.example.Vartaalap.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create a new comment
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam int userId, @RequestParam int articleId, @RequestParam String content, @RequestParam(defaultValue = "-1") long parentCommentId) {
        Comment createdComment = commentService.createComment(userId, articleId, content, parentCommentId);
        return ResponseEntity.ok(createdComment);
    }

    // Get a comment by ID
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findByCommentId(@PathVariable long commentId) {
        return ResponseEntity.ok(commentService.findByCommentId(commentId));
    }

    // Update comment content
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable long commentId, @RequestParam String newContent) {
        return ResponseEntity.ok(commentService.updateComment(commentId, newContent));
    }

    // Soft delete a comment
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    // Get all child comments for a parent comment
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/child")
    public ResponseEntity<List<Comment>> getAllChildComments(@RequestParam long parentCommentId) {
        return ResponseEntity.ok(commentService.getAllChildComments(parentCommentId));
    }

    // Get all comments made by a specific user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(commentService.findByUserId(userId));
    }

    // Get all root comments of an article
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Comment>> getCommentsByArticleId(@PathVariable long articleId) {
        return ResponseEntity.ok(commentService.findByArticleId(articleId));
    }
}
