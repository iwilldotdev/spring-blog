package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Post;

public interface PostService {

	Post save(Post post);

	List<Post> getAll();

	Optional<Post> getById(Long id);

	Post update(Post post);

	boolean delete(Long id);
}
