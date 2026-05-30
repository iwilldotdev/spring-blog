package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Comment;

public interface CommentService {

	Comment create(Comment comment);

	List<Comment> findAll();

	Optional<Comment> findById(Long id);

	Optional<Comment> update(Long id, Comment comment);

	boolean delete(Long id);
}
