package com.potatoes.potential.route.api;

import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.route.dto.RouteSaveReqDto;
import com.potatoes.potential.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @DeleteMapping("/{seq}")
    public ResponseEntity<BaseResponse> deleteRoute(@PathVariable Long seq) {
        return new ResponseEntity<>(routeService.deleteRoute(seq), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> saveRoute(@RequestBody RouteSaveReqDto reqDto) {
        return new ResponseEntity<>(routeService.saveRoute(reqDto), HttpStatus.OK);
    }

    @GetMapping("/{seq}")
    public ResponseEntity<BaseResponse> getRoute(@PathVariable Long seq) {
        return new ResponseEntity<>(routeService.getRoute(seq), HttpStatus.OK);
    }
}
