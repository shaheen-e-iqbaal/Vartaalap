package com.example.Vartaalap.Repository;

import com.example.Vartaalap.DTO.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentDTO, Long> {

    @Query(value = "select * from commentdto c where c.parent_comment_id = :parentCommentId order by createdon", nativeQuery = true)
    List<CommentDTO> findByParentCommentId(long parentCommentId);

    CommentDTO findByCommentId(long commentId);

    List<CommentDTO> findByUserId(long userId);


}
