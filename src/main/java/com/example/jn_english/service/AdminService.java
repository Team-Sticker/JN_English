package com.example.jn_english.service;

import com.example.jn_english.aws.S3Util;
import com.example.jn_english.controller.dto.request.PostRequest;
import com.example.jn_english.entity.admin.Admin;
import com.example.jn_english.entity.admin.AdminRepository;
import com.example.jn_english.entity.content.Content;
import com.example.jn_english.entity.content.ContentRepository;
import com.example.jn_english.entity.details.Details;
import com.example.jn_english.entity.post.Post;
import com.example.jn_english.entity.post.PostRepository;
import com.example.jn_english.exception.custom.AccountIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final PostRepository postRepository;

    private final ContentRepository contentRepository;


    private final S3Util s3Util;

    public void posting(PostRequest request) {
        Details details = (Details) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Admin admin = adminRepository.findByAccountId(details.getUsername())
                .orElseThrow(()-> AccountIdNotFoundException.EXCEPTION);
        Post post = postRepository.save(Post.builder()
                .title(request.getTitle())
                .tag(request.getTag())
                .admin(admin)
                .build());
        for (PostRequest.PostRequestContent content: request.getContents()) {
            contentRepository.save(Content.builder()
                            .content(content.getContent())
                            .type(content.getType())
                            .post(post)
                    .build());
        }
    }

    public String saveImage(MultipartFile file) {
        return s3Util.getS3ObjectUrl(s3Util.uploadImage(file, "image"));
    }
}
