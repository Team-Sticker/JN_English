package com.example.jn_english.service;

import com.example.jn_english.controller.dto.response.PostDetailResponse;
import com.example.jn_english.controller.dto.response.PostsResponse;
import com.example.jn_english.entity.content.Content;
import com.example.jn_english.entity.content.ContentRepository;
import com.example.jn_english.entity.enums.Tag;
import com.example.jn_english.entity.post.Post;
import com.example.jn_english.entity.post.PostRepository;
import com.example.jn_english.exception.custom.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private final PostRepository postRepository;

    private final ContentRepository contentRepository;

    public PostsResponse getGallery(Pageable page) {
        Page<Post> posts = postRepository.findByTagNot(Tag.announcement, page);
        return new PostsResponse(posts.getTotalPages(),
                posts.getContent().stream().map(post -> PostsResponse.PostResponse.builder()
                        .date(post.getCreatedDate())
                        .writer(post.getAdmin().getName())
                        .title(post.getTitle())
                        .id(post.getId())
                        .build()).collect(Collectors.toList()));
    }

    public PostsResponse getAnnouncement(Pageable page) {
        Page<Post> posts = postRepository.findByTag(Tag.announcement, page);
        return new PostsResponse(posts.getTotalPages(),
                posts.getContent().stream().map(post -> PostsResponse.PostResponse.builder()
                        .date(post.getCreatedDate())
                        .writer(post.getAdmin().getName())
                        .title(post.getTitle())
                        .id(post.getId())
                        .build()).collect(Collectors.toList()));
    }

    public PostDetailResponse getPostDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> PostNotFoundException.EXCEPTION);
        List<Content> contentList = contentRepository.findByPost(post);
        return PostDetailResponse.builder()
                .createdAt(post.getCreatedDate())
                .name(post.getAdmin().getName())
                .title(post.getTitle())
                .content(contentList.stream().map(Content::toPostRequestContent).collect(Collectors.toList()))
                .build();
    }
}
