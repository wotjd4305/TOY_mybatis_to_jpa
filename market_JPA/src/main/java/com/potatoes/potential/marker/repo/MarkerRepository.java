package com.potatoes.potential.marker.repo;

import com.potatoes.potential.marker.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MarkerRepository extends JpaRepository<Marker, Long> {

    Optional<Marker> findOneBySeq(Long Seq);
}
