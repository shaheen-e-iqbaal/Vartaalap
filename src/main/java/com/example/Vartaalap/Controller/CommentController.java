package com.example.Vartaalap.Controller;

import com.example.Vartaalap.DTO.CommentDTO;
import com.example.Vartaalap.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentController {

    CommentService commentService;

    CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.valueOf(500)).body(commentService.createComment(commentDTO));
    }

    @GetMapping(path = "/getcomment/{commentId}")
    public ResponseEntity<CommentDTO> findCommentByCommentId(@PathVariable int commentId){
        return ResponseEntity.ok().body(commentService.findByCommentId(commentId));
    }

    @PutMapping(path = "/update/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,
                                                    @PathVariable long commentId){
        commentDTO.setCommentId(commentId);
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(commentService.updateComment(commentDTO));
    }

    @DeleteMapping(path = "/delete/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable int commentId){
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(commentService.deleteComment(commentId));
    }

    @GetMapping(path = "/getchildcomments")
    public ResponseEntity<List<CommentDTO>> findByParentCommentId(@RequestParam long parentCommentId){
        return ResponseEntity.ok().body(commentService.getAllChildComments(parentCommentId));
    }

    @GetMapping(path = "getmycomments/{userId}")
    public ResponseEntity<List<CommentDTO>> findByUserId(@PathVariable long userId){
        return ResponseEntity.ok().body(commentService.findByUserId(userId));
    }

}
