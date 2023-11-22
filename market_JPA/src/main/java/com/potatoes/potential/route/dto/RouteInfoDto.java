package com.potatoes.potential.route.dto;

import com.potatoes.potential.common.dto.Coordinate;
import com.potatoes.potential.marker.dto.MarkerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteInfoDto {

    private Long seq;
    private List<Coordinate> coordinates;
    private List<MarkerDto> markerIds;
}
