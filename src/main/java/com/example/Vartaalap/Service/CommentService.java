package com.example.Vartaalap.Service;

import com.example.Vartaalap.DTO.CommentDTO;
import com.example.Vartaalap.Repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    //Method to save the Comment
    public CommentDTO createComment(CommentDTO commentDTO){
        return commentRepository.save(commentDTO);
    }

    //Method to find the all child comment of parent comment
    public List<CommentDTO> getAllChildComments(long parentCommentId){
        return commentRepository.findByParentCommentId(parentCommentId);
    }

    //Method to update the comment with commentId
    public CommentDTO updateComment(CommentDTO commentDTO){
        return commentRepository.save(commentDTO);
    }

    //Method to delete the comment with commentId
    public CommentDTO deleteComment(long commentId){
        CommentDTO commentDTO = commentRepository.findByCommentId(commentId);
        commentDTO.setCommentId(commentId);
        commentDTO.setContent("[Deleted]");
        return commentRepository.save(commentDTO);
    }


    //Method to find the Comment by commentId
    public CommentDTO findByCommentId(long commentId){
        return commentRepository.findByCommentId(commentId);
    }

    //Method to find all comment of user with userId
    public List<CommentDTO> findByUserId(long userId){
        return commentRepository.findByUserId(userId);
    }
}
