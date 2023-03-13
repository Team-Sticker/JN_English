package com.example.jn_english.controller.dto.request;

import com.example.jn_english.entity.enums.ContentType;
import com.example.jn_english.entity.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequest {
    private String title;

    private List<PostRequestContent> contents;

    private Tag tag;

    @Getter @AllArgsConstructor
    public static class PostRequestContent {
        private ContentType type;
        private String content;
    }
}
