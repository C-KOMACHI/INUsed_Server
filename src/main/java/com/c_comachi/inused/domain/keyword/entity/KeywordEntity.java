package com.c_comachi.inused.domain.keyword.entity;

import com.c_comachi.inused.domain.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="keyword")
public class KeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id", nullable = false)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @ManyToOne(cascade  = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Builder
    public KeywordEntity(Long id, String name, UserEntity user){
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
