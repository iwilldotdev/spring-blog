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
	public Post save(Post post) {
		if (post.getPostId() != null && postRepository.existsById(post.getPostId())) {
			throw new IllegalArgumentException("Post já existe.");
		}
		return postRepository.save(post);
	}

	@Override
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Override
	public Optional<Post> getById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post update(Post post) {
		if (post.getPostId() == null || !postRepository.existsById(post.getPostId())) {
			throw new IllegalArgumentException("Post não encontrado.");
		}
		return postRepository.save(post);
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
