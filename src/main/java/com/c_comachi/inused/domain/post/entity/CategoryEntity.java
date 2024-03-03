package com.c_comachi.inused.domain.post.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "category")
    private List<PostEntity> Posts = new ArrayList<>();

    @Builder
    public CategoryEntity(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
