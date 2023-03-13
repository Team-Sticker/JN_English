package com.example.jn_english.entity.content;

import com.example.jn_english.controller.dto.request.PostRequest;
import com.example.jn_english.entity.enums.ContentType;
import com.example.jn_english.entity.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private ContentType type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Content(String content, Post post, ContentType type) {
        this.content = content;
        this.type = type;
        this.post = post;
    }

    public PostRequest.PostRequestContent toPostRequestContent() {
        return new PostRequest.PostRequestContent(this.type, this.content);
    }
}
