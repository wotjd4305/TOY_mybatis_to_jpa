package com.potatoes.potential.article.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import com.potatoes.potential.article.domain.Article;
import com.potatoes.potential.article.domain.ArticleRepository;
import com.potatoes.potential.article.dto.ArticleDetailResponse;
import com.potatoes.potential.article.dto.ArticlesResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ArticleServiceTest {

    @Mock
    ArticleRepository articleRepository;
    @InjectMocks
    DefaultArticleService articleService;

    static Article article;

    @BeforeEach
    void initData() {
        MockitoAnnotations.openMocks(this);
        article = Article.builder()
            .id(UUID.randomUUID())
            .seq(1L)
            .contents("dummy")
            .articleImages(new ArrayList<>())
            .build();
    }

    @Test
    @DisplayName("Seq를 이용해 아티클을 조회할 수 있다")
    void test_when_find_article_by_seq() {
        doReturn(Optional.of(article)).when(articleRepository).findBySeq(1L);

        final ArticleDetailResponse response = articleService.getArticleDetail(1L);

        assertThat(response.getArticleSeq()).isEqualTo(1L);
        verify(articleRepository).findBySeq(1L);
    }


    @Test
    @DisplayName("전체 아티클을 조회할 수 있다")
    void test_find_all_article() {
        doReturn(List.of(article)).when(articleRepository).findAll();

        final ArticlesResponse response = articleService.getAllArticles();

        assertThat(response.totalCount()).isEqualTo(1);
        verify(articleRepository).findAll();
    }
}
