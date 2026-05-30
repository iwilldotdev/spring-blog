package com.descomplica.spring_blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descomplica.spring_blog.models.Post;
import com.descomplica.spring_blog.repositories.PostRepository;
import com.descomplica.spring_blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public Optional<Post> update(Long id, Post post) {
		return postRepository.findById(id).map(existing -> {
			post.setPostId(id);
			return postRepository.save(post);
		});
	}

	@Override
	public boolean delete(Long id) {
		if (!postRepository.existsById(id)) {
			return false;
		}
		postRepository.deleteById(id);
		return true;
	}
}
