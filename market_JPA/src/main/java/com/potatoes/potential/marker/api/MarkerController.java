package com.potatoes.potential.marker.api;

import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.marker.dto.MarkerPatchReqDto;
import com.potatoes.potential.marker.service.MarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/marker")
@RequiredArgsConstructor
public class MarkerController {

    private final MarkerService markerService;

    @PostMapping(value = "/{seq}", consumes = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BaseResponse> updateMarker(@PathVariable Long seq
            , @RequestPart MarkerPatchReqDto reqDto
            , @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(markerService.updateMarker(seq, reqDto, file), HttpStatus.OK);
    }

    @GetMapping("/{seq}")
    public ResponseEntity<BaseResponse> getMarker(@PathVariable Long seq) {
        return new ResponseEntity<>(markerService.getMarker(seq), HttpStatus.OK);
    }
}
