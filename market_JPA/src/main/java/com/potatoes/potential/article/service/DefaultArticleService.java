package com.potatoes.potential.article.service;

import com.potatoes.potential.article.domain.Article;
import com.potatoes.potential.article.domain.ArticleRepository;
import com.potatoes.potential.article.dto.ArticleDetailResponse;
import com.potatoes.potential.article.dto.ArticlesResponse;
import com.potatoes.potential.common.code.ErrorCode;
import com.potatoes.potential.common.exception.PotentialException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDetailResponse getArticleDetail(final Long articleSeq) {
        final Article findArticle =  articleRepository.findBySeq(articleSeq)
            .orElseThrow(() -> new PotentialException(ErrorCode.ARTICLE_NOT_FOUND.message()));
        return new ArticleDetailResponse(findArticle);
    }

    @Override
    public ArticlesResponse getAllArticles() {
        return articleRepository.findAll()
            .stream()
            .map(ArticleDetailResponse::new)
            .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), ArticlesResponse::new));
    }
}
