package com.potatoes.potential.article.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArticleImage {

    @Id
    @Column(name = "article_image_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "article_image_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String originImage;

    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    public String originImage() {
        return this.originImage;
    }

    public String thumbnail() {
        return this.thumbnail;
    }
}
