package com.potatoes.potential.route.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private Long seq;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String totalTime;
    private RouteInfoDto routeInfo;
}
