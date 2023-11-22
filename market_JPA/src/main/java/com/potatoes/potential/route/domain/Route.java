package com.potatoes.potential.route.domain;

import com.potatoes.potential.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route extends BaseEntity {

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(name = "route_seq", insertable = false, columnDefinition = "serial")
    private Long seq;

    @Id
    @Column(name = "route_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String totalTime;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_info_id")
    private RouteInfo routeInfo;
}
