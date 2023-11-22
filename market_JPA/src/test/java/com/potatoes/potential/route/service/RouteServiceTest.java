package com.potatoes.potential.route.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potatoes.potential.common.code.StatusEnum;
import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.common.dto.Coordinate;
import com.potatoes.potential.marker.dto.MarkerSaveDto;
import com.potatoes.potential.route.dto.RouteInfoSaveDto;
import com.potatoes.potential.route.dto.RouteSaveReqDto;
import com.potatoes.potential.marker.dto.MarkerDto;
import com.potatoes.potential.route.api.RouteController;
import com.potatoes.potential.route.dto.RouteDto;
import com.potatoes.potential.route.dto.RouteInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentRequest;
import static com.potatoes.potential.common.conf.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RouteController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class RouteServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RouteService routeService;

    @Test
    public void saveRoute() throws Exception {
        //given
        BaseResponse response = new BaseResponse(StatusEnum.OK, "", "1");

        given(routeService.saveRoute(any(RouteSaveReqDto.class)))
                .willReturn(response);

        //when
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate("143.212212", "29.123456"));
        coordinates.add(new Coordinate("142.212212", "28.123456"));
        coordinates.add(new Coordinate("141.212212", "27.123456"));

        ArrayList<MarkerSaveDto> markerIds = new ArrayList<>();
        markerIds.add(MarkerSaveDto.builder()
                .latitude("142.212212")
                .longitude("27.123456")
                .build());

        markerIds.add(MarkerSaveDto.builder()
                .latitude("142.212212")
                .longitude("27.123456")
                .build());

        RouteInfoSaveDto routeInfoDto = RouteInfoSaveDto
                .builder()
                .coordinates(coordinates)
                .markerIds(markerIds)
                .build();

        RouteSaveReqDto reqDto = RouteSaveReqDto
                .builder()
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .routeInfo(routeInfoDto)
                .build();

        ResultActions result = this.mockMvc.perform(
                post("/api/v1/route")
                        .content(objectMapper.writeValueAsString(reqDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("route-save",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("startDateTime").type(JsonFieldType.STRING).description("시작 시간"),
                                fieldWithPath("endDateTime").type(JsonFieldType.STRING).description("종료 시간"),
                                fieldWithPath("routeInfo.coordinates.[].latitude").type(JsonFieldType.STRING).description("위도"),
                                fieldWithPath("routeInfo.coordinates.[].longitude").type(JsonFieldType.STRING).description("경도"),
                                fieldWithPath("routeInfo.markerIds.[].latitude").type(JsonFieldType.STRING).description("마커 위도"),
                                fieldWithPath("routeInfo.markerIds.[].longitude").type(JsonFieldType.STRING).description("마커 경도")

                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("데이터")

                        )
                ));
    }
    
    @Test
    public void getRoute() throws Exception {
        //given
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate("143.212212", "29.123456"));
        coordinates.add(new Coordinate("142.212212", "28.123456"));
        coordinates.add(new Coordinate("141.212212", "27.123456"));

        ArrayList<MarkerDto> markerIds = new ArrayList<>();
        markerIds.add(MarkerDto.builder()
                .seq(1L)
                .latitude("142.212212")
                .longitude("27.123456")
                .imageId(null)
                .build());

        markerIds.add(MarkerDto.builder()
                .seq(2L)
                .latitude("142.212212")
                .longitude("27.123456")
                .imageId(1L)
                .build());

        RouteInfoDto RouteInfo = RouteInfoDto.builder()
                .seq(1L)
                .coordinates(coordinates)
                .markerIds(markerIds)
                .build();

        RouteDto route = RouteDto.builder()
                .seq(1L)
                .routeInfo(RouteInfo)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .totalTime("01:34:12")
                .build();

        System.out.println(route.toString());
        BaseResponse response = new BaseResponse(StatusEnum.OK, "", route);
        given(routeService.getRoute(any(Long.class)))
                .willReturn(response);

        //when
        Long seq = 1L;

        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.
                get("/api/v1/route/{seq}", ((RouteDto)response.getData()).getSeq())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("route-get",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("seq").description("경로 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data.seq").type(JsonFieldType.NUMBER).description("경로 시퀀스"),
                                fieldWithPath("data.startDateTime").type(JsonFieldType.STRING).description("시작 시간"),
                                fieldWithPath("data.endDateTime").type(JsonFieldType.STRING).description("종료 시간"),
                                fieldWithPath("data.totalTime").type(JsonFieldType.STRING).description("총 수행 시간"),
                                fieldWithPath("data.routeInfo.seq").type(JsonFieldType.NUMBER).description("경로 정보 시퀀스"),
                                fieldWithPath("data.routeInfo.coordinates.[].latitude").type(JsonFieldType.STRING).description("위도"),
                                fieldWithPath("data.routeInfo.coordinates.[].longitude").type(JsonFieldType.STRING).description("경도"),
                                fieldWithPath("data.routeInfo.markerIds.[].seq").type(JsonFieldType.NUMBER).description("마커 시퀀스"),
                                fieldWithPath("data.routeInfo.markerIds.[].latitude").type(JsonFieldType.STRING).description("마커 위도"),
                                fieldWithPath("data.routeInfo.markerIds.[].longitude").type(JsonFieldType.STRING).description("마커 경도"),
                                fieldWithPath("data.routeInfo.markerIds.[].imageId").type(JsonFieldType.NUMBER).description("이미지 아이디").optional()
                        )
                ));
    }
    
     @Test
    public void deleteRoute() throws Exception {
        //given
        BaseResponse response = new BaseResponse(StatusEnum.OK, "", "1");
        given(routeService.deleteRoute(any(Long.class)))
                .willReturn(response);

        //when
        ResultActions result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.
                        delete("/api/v1/route/{seq}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("route-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("seq").description("경로 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING).description("데이터")
                        )
                ));
    }

}

