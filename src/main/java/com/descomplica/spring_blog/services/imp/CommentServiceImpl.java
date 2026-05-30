package com.descomplica.spring_blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descomplica.spring_blog.models.Comment;
import com.descomplica.spring_blog.repositories.CommentRepository;
import com.descomplica.spring_blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment create(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}

	@Override
	public Optional<Comment> update(Long id, Comment comment) {
		return commentRepository.findById(id).map(existing -> {
			comment.setCommentId(id);
			return commentRepository.save(comment);
		});
	}

	@Override
	public boolean delete(Long id) {
		if (!commentRepository.existsById(id)) {
			return false;
		}
		commentRepository.deleteById(id);
		return true;
	}
}
