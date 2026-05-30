package com.descomplica.spring_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descomplica.spring_blog.models.Comment;
import com.descomplica.spring_blog.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Comment comment) {
		try {
			Comment savedComment = commentService.save(comment);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}

	@GetMapping("/getAll")
	public List<Comment> getAll() {
		return commentService.getAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return commentService.getById(id)
				.map(comment -> ResponseEntity.ok((Object) comment))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado."));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Comment comment) {
		try {
			Comment updatedComment = commentService.update(comment);
			return ResponseEntity.ok(updatedComment);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!commentService.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado.");
		}
		return ResponseEntity.ok("Comentário removido com sucesso.");
	}
}
