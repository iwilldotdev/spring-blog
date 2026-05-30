package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Comment;

public interface CommentService {

	Comment save(Comment comment);

	List<Comment> getAll();

	Optional<Comment> getById(Long id);

	Comment update(Comment comment);

	boolean delete(Long id);
}
