package com.descomplica.spring_blog.services;

import java.util.List;
import java.util.Optional;

import com.descomplica.spring_blog.models.Tag;

public interface TagService {

	Tag create(Tag tag);

	List<Tag> findAll();

	Optional<Tag> findById(Long id);

	Optional<Tag> update(Long id, Tag tag);

	boolean delete(Long id);
}
