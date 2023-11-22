package com.potatoes.potential.marker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potatoes.potential.common.code.StatusEnum;
import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.marker.api.MarkerController;
import com.potatoes.potential.marker.dto.MarkerPatchReqDto;
import com.potatoes.potential.marker.dto.MarkerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;

import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentRequest;
import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MarkerController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
class MarkerServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MarkerService markerService;

    @Test
    public void getMarker() throws Exception {
        //given
        MarkerDto marker = MarkerDto.builder()
                .seq(1L)
                .latitude("143.212212")
                .longitude("29.123456")
                .imageId(1L)
                .build();

        BaseResponse response = new BaseResponse(StatusEnum.OK, "", marker);
        given(markerService.getMarker(any(Long.class)))
                .willReturn(response);

        //when
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.
                        get("/api/v1/marker/{seq}", ((MarkerDto) response.getData()).getSeq())
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("marker-get",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("seq").description("경로 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data.seq").type(JsonFieldType.NUMBER).description("경로 시퀀스"),
                                fieldWithPath("data.latitude").type(JsonFieldType.STRING).description("시작 시간"),
                                fieldWithPath("data.longitude").type(JsonFieldType.STRING).description("종료 시간"),
                                fieldWithPath("data.imageId").type(JsonFieldType.NUMBER).description("이미지 아이디")
                        )
                ));
    }

    @Test
    public void updateMarker() throws Exception {
        //given
        BaseResponse response = new BaseResponse(StatusEnum.OK, "", "1");
        given(markerService.updateMarker(any(Long.class), any(MarkerPatchReqDto.class), any()))
                .willReturn(response);

        //when
        Long seq = 1L;
        MarkerPatchReqDto markerPatchReqDto = MarkerPatchReqDto.builder()
                .latitude("143.212212")
                .longitude("29.123456")
                .build();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "imageFile.png", "image/png", "<<png data>>".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile reqDto = new MockMultipartFile("reqDto", "", "application/json", objectMapper.writeValueAsString(markerPatchReqDto).getBytes());

        ResultActions result = this.mockMvc.perform(
                multipart("/api/v1/marker/{seq}", seq)
                        .file(mockMultipartFile)
                        .file(reqDto)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("marker-update",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("seq").description("경로 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("시퀀스 번호")
                        )
                ));
    }
}
