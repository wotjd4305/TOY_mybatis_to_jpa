package com.potatoes.potential.route.repo;

import com.potatoes.potential.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Route findOneById(UUID Uuid);

    Route findOneBySeq(Long Seq);

    Long deleteBySeq(Long Seq);
}
