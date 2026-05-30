package com.descomplica.spring_blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descomplica.spring_blog.models.Tag;
import com.descomplica.spring_blog.repositories.TagRepository;
import com.descomplica.spring_blog.services.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Tag create(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Optional<Tag> findById(Long id) {
		return tagRepository.findById(id);
	}

	@Override
	public Optional<Tag> update(Long id, Tag tag) {
		return tagRepository.findById(id).map(existing -> {
			tag.setTagId(id);
			return tagRepository.save(tag);
		});
	}

	@Override
	public boolean delete(Long id) {
		if (!tagRepository.existsById(id)) {
			return false;
		}
		tagRepository.deleteById(id);
		return true;
	}
}
