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

import com.descomplica.spring_blog.models.Post;
import com.descomplica.spring_blog.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Post post) {
		try {
			Post savedPost = postService.save(post);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}

	@GetMapping("/getAll")
	public List<Post> getAll() {
		return postService.getAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return postService.getById(id)
				.map(post -> ResponseEntity.ok((Object) post))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado."));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Post post) {
		try {
			Post updatedPost = postService.update(post);
			return ResponseEntity.ok(updatedPost);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!postService.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post não encontrado.");
		}
		return ResponseEntity.ok("Post removido com sucesso.");
	}
}
