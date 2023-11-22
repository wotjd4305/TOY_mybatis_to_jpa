package com.potatoes.potential.route.service;

import com.potatoes.potential.common.code.StatusEnum;
import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.marker.domain.Marker;
import com.potatoes.potential.marker.repo.MarkerRepository;
import com.potatoes.potential.route.dto.RouteSaveReqDto;
import com.potatoes.potential.route.domain.Route;
import com.potatoes.potential.route.dto.RouteDto;
import com.potatoes.potential.route.repo.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final MarkerRepository markerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BaseResponse saveRoute(RouteSaveReqDto reqDto) {
        reqDto.calTotalTime();
        Route route = modelMapper.map(reqDto, Route.class);
        routeRepository.saveAndFlush(route);

        reqDto.getRouteInfo()
                .getMarkerIds()
                .stream()
                .map(m -> new Marker(route, m))
                .forEach(markerRepository::save);

        return new BaseResponse(StatusEnum.OK, StatusEnum.OK.getCode(), route.getSeq());
    }

    @Transactional(readOnly = true)
    public BaseResponse getRoute(Long seq) {
        Route route = routeRepository.findOneBySeq(seq);
        RouteDto routeDto = modelMapper.map(route, RouteDto.class);
        return new BaseResponse(StatusEnum.OK, StatusEnum.OK.getCode(), routeDto);
    }

    public BaseResponse deleteRoute(Long seq) {
        Long result = routeRepository.deleteBySeq(seq);
        return new BaseResponse(StatusEnum.OK, StatusEnum.OK.getCode(), result);
    }
}
