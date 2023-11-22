package com.potatoes.potential.marker.domain;

import com.potatoes.potential.common.BaseEntity;
import com.potatoes.potential.marker.dto.MarkerSaveDto;
import com.potatoes.potential.route.domain.Route;
import com.potatoes.potential.route.domain.RouteInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marker extends BaseEntity {

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(name = "marker_seq", insertable = false, updatable = false, columnDefinition = "serial")
    private Long seq;

    @Id
    @Column(name = "marker_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private String latitude;
    private String longitude;
    private Long imageId;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_info_id")
    private RouteInfo routeInfo;

    public Marker(Route route, MarkerSaveDto markerSaveDto) {
        routeInfo = route.getRouteInfo();
        latitude = markerSaveDto.getLatitude();
        longitude = markerSaveDto.getLongitude();
    }

}
