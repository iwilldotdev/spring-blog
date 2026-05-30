package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Tag;

public interface TagService {

	Tag save(Tag tag);

	List<Tag> getAll();

	Optional<Tag> getById(Long id);

	Tag update(Tag tag);

	boolean delete(Long id);
}
