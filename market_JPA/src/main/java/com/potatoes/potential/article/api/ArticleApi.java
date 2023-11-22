package com.potatoes.potential.article.api;

import com.potatoes.potential.article.dto.ArticleDetailResponse;
import com.potatoes.potential.article.dto.ArticlesResponse;
import com.potatoes.potential.article.service.ArticleService;
import com.potatoes.potential.common.dto.BaseResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleApi {

    private final ArticleService articleService;

    @GetMapping("/{articleSeq}")
    public BaseResponseEntity<ArticleDetailResponse> getArticleDetail(@PathVariable("articleSeq") final Long articleSeq) {
        return new BaseResponseEntity<>(articleService.getArticleDetail(articleSeq));
    }

    @GetMapping
    public BaseResponseEntity<ArticlesResponse> getArticles() {
        return new BaseResponseEntity<>(articleService.getAllArticles());
    }
}
