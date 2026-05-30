package com.descomplica.spring_blog.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long tagId;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "tags")
	private Set<Post> posts = new HashSet<>();

	public Tag() {
	}

	public Tag(final Long tagId, final String name, final Set<Post> posts) {
		this.tagId = tagId;
		this.name = name;
		this.posts = posts;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}
