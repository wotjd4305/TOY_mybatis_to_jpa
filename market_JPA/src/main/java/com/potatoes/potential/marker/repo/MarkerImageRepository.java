package com.potatoes.potential.marker.repo;

import com.potatoes.potential.marker.domain.MarkerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MarkerImageRepository extends JpaRepository<MarkerImage, Long> {

    MarkerImage findOneById(UUID uuid);
}
