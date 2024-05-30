package com.sparta.java_personal_task_3.repository;

import com.sparta.java_personal_task_3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT MAX(Comment .id) FROM Comment", nativeQuery = true)
    Long findMaxId();
}
