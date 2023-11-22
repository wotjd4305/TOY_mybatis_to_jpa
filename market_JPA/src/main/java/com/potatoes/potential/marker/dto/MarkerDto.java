package com.potatoes.potential.marker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.potatoes.potential.marker.domain.Marker;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerDto {

    private Long seq;
    private String latitude;
    private String longitude;
    private Long imageId;

    public MarkerDto(Marker marker) {
        this.seq = marker.getSeq();
        this.latitude = marker.getLatitude();
        this.longitude = marker.getLongitude();
        this.imageId = marker.getImageId();
    }

    public MarkerDto(MarkerPatchReqDto marker, Long imageId) {
        this.latitude = marker.getLatitude();
        this.longitude = marker.getLongitude();
        this.imageId = imageId;
    }

}
