package com.dev.hlog.repository;

import com.dev.hlog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
}
