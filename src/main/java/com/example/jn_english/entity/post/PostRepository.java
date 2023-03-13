package com.example.jn_english.entity.post;

import com.example.jn_english.entity.enums.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTagNot(Tag tag, Pageable pageable);

    Page<Post> findByTag(Tag tag, Pageable pageable);
    Optional<Post> findByTitle(String title);
}
