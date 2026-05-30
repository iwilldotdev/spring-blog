package com.descomplica.spring_blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descomplica.spring_blog.models.User;
import com.descomplica.spring_blog.repositories.UserRepository;
import com.descomplica.spring_blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> update(Long id, User user) {
		return userRepository.findById(id).map(existing -> {
			user.setUserId(id);
			return userRepository.save(user);
		});
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
