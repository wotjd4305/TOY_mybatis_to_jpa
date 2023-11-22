package com.potatoes.potential.route.domain;

import com.potatoes.potential.common.BaseEntity;
import com.potatoes.potential.common.dto.Coordinate;
import com.potatoes.potential.marker.domain.Marker;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor
@AllArgsConstructor
public class RouteInfo extends BaseEntity {

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(name = "route_info_seq", insertable = false, updatable = false, columnDefinition = "serial")
    private Long seq;

    @Id
    @Column(name = "route_info_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Type(type = "jsonb")
    @Column(name = "coordinate", columnDefinition = "jsonb")
    private List<Coordinate> coordinates;

    @Type(type = "list-array")
    @OneToMany(mappedBy = "routeInfo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Marker> markerIds;
}
