package com.dev.hlog.repository;

import com.dev.hlog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
}
