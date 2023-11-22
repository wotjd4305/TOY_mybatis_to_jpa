package com.potatoes.potential.marker.domain;

import com.potatoes.potential.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerImage extends BaseEntity {

    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    @Column(name = "marker_image_seq", insertable = false, updatable = false, columnDefinition = "serial")
    private Long seq;

    @Id
    @Column(name = "marker_image_id")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    private String originImage;
    private String thumbnail;
}
