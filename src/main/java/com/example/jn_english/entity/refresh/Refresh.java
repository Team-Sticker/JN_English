package com.example.jn_english.entity;

import com.example.jn_english.entity.admin.Admin;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String refresh;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder
    public Refresh(String refresh, Admin admin) {
        this.admin = admin;
        this.refresh = refresh;
    }
}
