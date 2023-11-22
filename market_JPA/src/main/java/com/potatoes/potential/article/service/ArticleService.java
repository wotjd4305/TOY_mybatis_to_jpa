package com.potatoes.potential.article.service;

import com.potatoes.potential.article.dto.ArticleDetailResponse;
import com.potatoes.potential.article.dto.ArticlesResponse;

public interface ArticleService {

    ArticleDetailResponse getArticleDetail(Long articleSeq);

    ArticlesResponse getAllArticles();
}
