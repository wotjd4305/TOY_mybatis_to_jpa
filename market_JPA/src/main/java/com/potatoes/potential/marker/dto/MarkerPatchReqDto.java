package com.potatoes.potential.marker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerPatchReqDto {

    private String latitude;
    private String longitude;

    @JsonIgnore
    private Long imageSeq;
}
