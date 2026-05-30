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
	public Comment save(Comment comment) {
		if (comment.getCommentId() != null && commentRepository.existsById(comment.getCommentId())) {
			throw new IllegalArgumentException("Comentário já existe.");
		}
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getAll() {
		return commentRepository.findAll();
	}

	@Override
	public Optional<Comment> getById(Long id) {
		return commentRepository.findById(id);
	}

	@Override
	public Comment update(Comment comment) {
		if (comment.getCommentId() == null || !commentRepository.existsById(comment.getCommentId())) {
			throw new IllegalArgumentException("Comentário não encontrado.");
		}
		return commentRepository.save(comment);
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
