package com.potatoes.potential.article.dto;

import com.potatoes.potential.article.domain.Article;
import java.util.List;
import lombok.Getter;

@Getter
public class ArticleDetailResponse {

    private final Long articleSeq;
    private final String contents;
    private final List<String> originImages;
    private final List<String> thumbnails;

    public ArticleDetailResponse(final Article article) {
        this.articleSeq = article.seq();
        this.contents = article.contents();
        this.originImages = article.originImages();
        this.thumbnails = article.thumbnails();
    }

    public ArticleDetailResponse(Long articleSeq, String contents, List<String> originImages, List<String> thumbnails) {
        this.articleSeq = articleSeq;
        this.contents = contents;
        this.originImages = originImages;
        this.thumbnails = thumbnails;
    }
}
