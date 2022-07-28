package com.ssafy.sai.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class FavoriteEnterprise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_enterprise_id")
    private Long id;

    @OneToMany(mappedBy = "favoriteEnterprise")
    private List<Enterprise> enterprises = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}