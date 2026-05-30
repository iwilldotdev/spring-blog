package com.descomplica.spring_blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.descomplica.spring_blog.models.User;
import com.descomplica.spring_blog.repositories.UserRepository;
import com.descomplica.spring_blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {
		if (user.getUserId() != null && userRepository.existsById(user.getUserId())) {
			throw new IllegalArgumentException("Usuário já existe.");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("E-mail já cadastrado.");
		}
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new IllegalArgumentException("Nome de usuário já cadastrado.");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User update(User user) {
		if (user.getUserId() == null || !userRepository.existsById(user.getUserId())) {
			throw new IllegalArgumentException("Usuário não encontrado.");
		}
		return userRepository.save(user);
	}

	@Override
	public boolean delete(Long id) {
		if (!userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}
}
