package com.example.jn_english.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponse {
    private final int totalPages;
    private List<PostResponse> postResponses;

    @Getter @Builder
    public static class PostResponse {
        private final String title;
        private final LocalDate date;
        private final String writer;
        private final Long id;
    }

}
