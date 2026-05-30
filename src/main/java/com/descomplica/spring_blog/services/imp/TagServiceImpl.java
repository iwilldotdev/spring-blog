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
	public Tag save(Tag tag) {
		if (tag.getTagId() != null && tagRepository.existsById(tag.getTagId())) {
			throw new IllegalArgumentException("Tag já existe.");
		}
		if (tagRepository.existsByName(tag.getName())) {
			throw new IllegalArgumentException("Tag já cadastrada.");
		}
		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> getAll() {
		return tagRepository.findAll();
	}

	@Override
	public Optional<Tag> getById(Long id) {
		return tagRepository.findById(id);
	}

	@Override
	public Tag update(Tag tag) {
		if (tag.getTagId() == null || !tagRepository.existsById(tag.getTagId())) {
			throw new IllegalArgumentException("Tag não encontrada.");
		}
		return tagRepository.save(tag);
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
