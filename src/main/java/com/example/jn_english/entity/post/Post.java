package com.example.jn_english.entity;

import com.example.jn_english.entity.tag.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Tag tag; // 나중에 enum 으로 바꿀것

    private String image;

    @Builder
    public Post(String title, String content, String image, Tag tag) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.tag = tag;
    }

}
