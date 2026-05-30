package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.User;

public interface UserService {

	User save(User user);

	List<User> getAll();

	Optional<User> getById(Long id);

	User update(User user);

	boolean delete(Long id);
}
