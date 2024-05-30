package com.sparta.java_personal_task_3.repository;

import com.sparta.java_personal_task_3.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
