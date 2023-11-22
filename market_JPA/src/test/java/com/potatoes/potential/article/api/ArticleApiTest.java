package com.potatoes.potential.article.api;

import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentRequest;
import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.potatoes.potential.article.dto.ArticleDetailResponse;
import com.potatoes.potential.article.dto.ArticlesResponse;
import com.potatoes.potential.article.service.ArticleService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArticleApi.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class ArticleApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleService articleService;

    @Test
    @DisplayName("Seq를 이용해 아티클을 조회할 수 있다")
    void test_when_get_article_using_seq() throws Exception {
        final ArticleDetailResponse detailResponse = new ArticleDetailResponse(1L, "contents", List.of("originUrl"),
            List.of("thumbnailUrl"));
        when(articleService.getArticleDetail(1L))
            .thenReturn(detailResponse);

        this.mvc.perform(get("/api/v1/article/{articleSeq}", detailResponse.getArticleSeq())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("article-get",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("articleSeq").description("게시글 시퀀스")
                ),
                responseFields(
                    fieldWithPath("resultCode").type(JsonFieldType.NUMBER).description("응답 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 본문"),
                    fieldWithPath("data.articleSeq").type(JsonFieldType.NUMBER).description("게시글 시퀀스"),
                    fieldWithPath("data.contents").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("data.originImages").type(JsonFieldType.ARRAY).description("원본 이미지 urls"),
                    fieldWithPath("data.thumbnails").type(JsonFieldType.ARRAY).description("썸네일 이미지 urls")
                )
            ));
    }

    @Test
    @DisplayName("전체 아티클을 조회할 수 있다")
    void test_when_get_all_articles() throws Exception {
        final ArticleDetailResponse detailResponse = new ArticleDetailResponse(1L, "contents", List.of("originUrl"),
            List.of("thumbnailUrl"));
        when(articleService.getAllArticles())
            .thenReturn(new ArticlesResponse(List.of(detailResponse)));
        this.mvc.perform(get("/api/v1/article/")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("article-get-all",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("resultCode").type(JsonFieldType.NUMBER).description("응답 코드"),
                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메세지"),
                    fieldWithPath("data").type(JsonFieldType.OBJECT).description("응답 본문"),
                    fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("전체 게시글 수"),
                    fieldWithPath("data.articles").type(JsonFieldType.ARRAY).description("게시글 배열"),
                    fieldWithPath("data.articles.[].articleSeq").type(JsonFieldType.NUMBER).description("게시글 시퀀스"),
                    fieldWithPath("data.articles.[].contents").type(JsonFieldType.STRING).description("게시글 본문"),
                    fieldWithPath("data.articles.[].originImages").type(JsonFieldType.ARRAY).description("원본 이미지 urls"),
                    fieldWithPath("data.articles.[].thumbnails").type(JsonFieldType.ARRAY).description("썸네일 이미지 urls")
                )
            ));
    }
}
