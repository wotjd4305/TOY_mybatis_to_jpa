package com.potatoes.potential.article.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article {

    @Id
    @Column(name = "article_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "article_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Lob
    private String contents;

    @JsonIgnore
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<ArticleImage> articleImages;

    public Long seq() {
        return this.seq;
    }

    public String contents() {
        return contents;
    }

    public List<String> originImages() {
        return articleImages.stream()
            .map(ArticleImage::originImage)
            .toList();
    }

    public List<String> thumbnails() {
        return articleImages.stream()
            .map(ArticleImage::thumbnail)
            .toList();
    }
}
