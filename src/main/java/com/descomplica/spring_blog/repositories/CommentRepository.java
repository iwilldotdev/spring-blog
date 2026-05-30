package com.descomplica.spring_blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.descomplica.spring_blog.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
