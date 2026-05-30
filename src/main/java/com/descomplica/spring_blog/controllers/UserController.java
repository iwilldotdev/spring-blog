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

import com.descomplica.spring_blog.models.User;
import com.descomplica.spring_blog.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			User savedUser = userService.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}

	@GetMapping("/getAll")
	public List<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return userService.getById(id)
				.map(user -> ResponseEntity.ok((Object) user))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			User updatedUser = userService.update(user);
			return ResponseEntity.ok(updatedUser);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!userService.delete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
		}
		return ResponseEntity.ok("Usuário removido com sucesso.");
	}
}
