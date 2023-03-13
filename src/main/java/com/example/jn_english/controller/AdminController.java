package com.example.jn_english.controller;

import com.example.jn_english.controller.dto.request.PostRequest;
import com.example.jn_english.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {
    private final AdminService adminService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public void posting(@RequestBody PostRequest request) {
        adminService.posting(request);
    }

    @PostMapping("/image")
    public String saveImage(@RequestParam("file") MultipartFile file) {
        return adminService.saveImage(file);
    }
}
