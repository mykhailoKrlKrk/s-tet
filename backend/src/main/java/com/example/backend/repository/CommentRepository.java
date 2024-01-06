package com.example.backend.repository;

import com.example.backend.model.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT DISTINCT cm FROM Comment cm WHERE cm.category.name = :category "
            + "AND cm.isDeleted = false")
    List<Comment> getCommentsByCategory(@Param("category") String category);

    Optional<Comment> getCommentByCategoryName(String category);
}
