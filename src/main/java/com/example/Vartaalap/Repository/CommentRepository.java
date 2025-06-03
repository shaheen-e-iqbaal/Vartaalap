package com.example.Vartaalap.Repository;

import com.example.Vartaalap.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from commentdto c where c.parent_comment_id = :parentCommentId order by createdon", nativeQuery = true)
    List<Comment> findByParentCommentId(long parentCommentId);

    Comment findByCommentId(long commentId);


    List<Comment> findByUserUserId(int userId);

    List<Comment> findByArticleArticleIdAndParentCommentId(long articleId, long parentCommentId);

}
