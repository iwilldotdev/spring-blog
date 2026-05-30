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

import com.descomplica.spring_blog.models.Tag;
import com.descomplica.spring_blog.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Tag tag) {
		try {
			Tag savedTag = tagService.save(tag);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}

	@GetMapping("/getAll")
	public List<Tag> getAll() {
		return tagService.getAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return tagService.getById(id)
				.map(tag -> ResponseEntity.ok((Object) tag))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag não encontrada."));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Tag tag) {
		try {
			Tag updatedTag = tagService.update(tag);
			return ResponseEntity.ok(updatedTag);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!tagService.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag não encontrada.");
		}
		return ResponseEntity.ok("Tag removida com sucesso.");
	}
}
