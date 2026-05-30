package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Post;

public interface PostService {

	Post create(Post post);

	List<Post> findAll();

	Optional<Post> findById(Long id);

	Optional<Post> update(Long id, Post post);

	boolean delete(Long id);
}
