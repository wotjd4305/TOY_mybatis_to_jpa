package com.potatoes.potential.route.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RouteSaveReqDtoTest {

    @ParameterizedTest
    @MethodSource("calTotalTime_parameter")
    void calTotalTime(LocalDateTime startDateTime, LocalDateTime endDateTime, String totalTime) {
        RouteSaveReqDto reqDto = RouteSaveReqDto.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
        reqDto.calTotalTime();
        assertThat(reqDto.getTotalTime(), is(totalTime));
    }

    private static Stream<Arguments> calTotalTime_parameter() { // argument source method
        return Stream.of(
                Arguments.of(LocalDateTime.of(2022, 07, 12, 04, 30, 30),
                        LocalDateTime.of(2022, 07, 12, 04, 30, 50),
                        "00:00:20"),
                Arguments.of(LocalDateTime.of(2022, 07, 12, 04, 30, 30),
                        LocalDateTime.of(2022, 07, 12, 04, 43, 50),
                        "00:13:20"),
                Arguments.of(LocalDateTime.of(2022, 07, 12, 04, 30, 30),
                        LocalDateTime.of(2022, 07, 12, 12, 33, 50),
                        "08:03:20"),
                Arguments.of(LocalDateTime.of(2022, 07, 12, 04, 30, 30),
                        LocalDateTime.of(2022, 07, 13, 12, 33, 50),
                        "32:03:20"),
                Arguments.of(LocalDateTime.of(2022, 07, 12, 04, 30, 30),
                        LocalDateTime.of(2022, 07, 19, 12, 33, 50),
                        "176:03:20")
                );
    }
}
