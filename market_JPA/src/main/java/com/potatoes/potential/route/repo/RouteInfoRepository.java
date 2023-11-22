package com.potatoes.potential.route.repo;

import com.potatoes.potential.route.domain.RouteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteInfoRepository extends JpaRepository<RouteInfo, Long> {

}
