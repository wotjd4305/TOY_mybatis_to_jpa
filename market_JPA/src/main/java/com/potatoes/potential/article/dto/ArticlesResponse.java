package com.potatoes.potential.article.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ArticlesResponse {

    private final List<ArticleDetailResponse> articles;
    private final int totalCount;

    public ArticlesResponse(List<ArticleDetailResponse> articles) {
        this.articles = articles;
        this.totalCount = articles.size();
    }

    public int totalCount() {
        return totalCount;
    }
}
