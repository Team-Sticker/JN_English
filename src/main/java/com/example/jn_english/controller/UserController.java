package com.example.jn_english.controller;

import com.example.jn_english.controller.dto.response.PostDetailResponse;
import com.example.jn_english.controller.dto.response.PostsResponse;
import com.example.jn_english.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/jn-gallery")
    public PostsResponse getGallery(@PageableDefault Pageable pageable) {
        return userService.getGallery(pageable);
    }

    @GetMapping("/announcement")
    public PostsResponse getAnnouncement(@PageableDefault Pageable pageable) {
        return userService.getAnnouncement(pageable);
    }

    @GetMapping("/{id}")
    public PostDetailResponse getPostDetail(@PathVariable Long id) {
        return userService.getPostDetail(id);
    }
}
