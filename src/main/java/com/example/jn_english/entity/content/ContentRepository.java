package com.example.jn_english.entity.content;

import com.example.jn_english.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByPost(Post post);
}
