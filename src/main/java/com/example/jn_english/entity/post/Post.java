package com.example.jn_english.entity.post;

import com.example.jn_english.entity.admin.Admin;
import com.example.jn_english.entity.enums.Tag;
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
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    @Column(nullable = false)
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder
    public Post(String title, Tag tag, Admin admin) {
        this.title = title;
        this.tag = tag;
        this.admin = admin;
        this.createdDate = LocalDate.now();
    }

}
