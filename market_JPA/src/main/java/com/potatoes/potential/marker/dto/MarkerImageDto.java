package com.potatoes.potential.marker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerImageDto {

    private Long seq;
    private String originImage;
    private String thumbnail;
}
