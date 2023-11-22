package com.potatoes.potential.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {

    @JsonProperty
    private String latitude;

    @JsonProperty
    private String longitude;
}
