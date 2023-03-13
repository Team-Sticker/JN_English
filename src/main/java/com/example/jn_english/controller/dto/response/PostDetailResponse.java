package com.example.jn_english.controller.dto.response;

import com.example.jn_english.controller.dto.request.PostRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PostDetailResponse {
    private final String title;

    private final String name;

    private final List<PostRequest.PostRequestContent> contents;

    private final LocalDate createdAt;

    @Builder
    public PostDetailResponse(String title, String name, List<PostRequest.PostRequestContent> content, LocalDate createdAt) {
        this.title = title;
        this.name = name;
        this.contents = content;
        this.createdAt = createdAt;
    }
}
