package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.User;

public interface UserService {

	User create(User user);

	List<User> findAll();

	Optional<User> findById(Long id);

	Optional<User> update(Long id, User user);

	boolean delete(Long id);
}
