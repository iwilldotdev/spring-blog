package com.descomplica.spring_blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.descomplica.spring_blog.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
