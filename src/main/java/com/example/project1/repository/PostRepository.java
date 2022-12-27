package com.example.project1.repository;

import com.example.project1.domain.dto.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContaining(Pageable pageable,String title);

}
