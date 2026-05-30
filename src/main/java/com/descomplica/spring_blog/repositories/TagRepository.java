package com.descomplica.spring_blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.descomplica.spring_blog.models.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	boolean existsByName(String name);
}
