package com.potatoes.potential.marker.service;

import com.potatoes.potential.common.code.StatusEnum;
import com.potatoes.potential.common.dto.BaseResponse;
import com.potatoes.potential.common.dto.FileSaveDto;
import com.potatoes.potential.common.utils.DateUtil;
import com.potatoes.potential.common.utils.FileManager;
import com.potatoes.potential.marker.domain.Marker;
import com.potatoes.potential.marker.domain.MarkerImage;
import com.potatoes.potential.marker.dto.MarkerDto;
import com.potatoes.potential.marker.dto.MarkerPatchReqDto;
import com.potatoes.potential.marker.repo.MarkerImageRepository;
import com.potatoes.potential.marker.repo.MarkerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MarkerService {

    private final MarkerRepository markerRepository;
    private final MarkerImageRepository markerImageRepository;

    private final FileManager fileManager;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public BaseResponse getMarker(Long seq) {
        Marker findMarker = markerRepository.findOneBySeq(seq)
                .orElseThrow(() -> new IllegalArgumentException());
        MarkerDto markerDto = new MarkerDto(findMarker);
        return new BaseResponse(StatusEnum.OK, StatusEnum.OK.getCode(), markerDto);
    }

    public BaseResponse updateMarker(Long seq,
                                     MarkerPatchReqDto reqDto,
                                     MultipartFile file) {
        Marker findMarker = markerRepository.findOneBySeq(seq)
                .orElseThrow(() -> new IllegalArgumentException());

        String orginImgFileName = makeFilePrefixName(findMarker.getId()) + "markerImageOrg.png";
        String thumbnailImgFileName = makeFilePrefixName(findMarker.getId()) + "markerImageThumbnail.png";

        String originImageUrl = fileManager.saveResizedFile(new FileSaveDto(thumbnailImgFileName, File.separator + "image", file), "png");
        String thumbnailUrl = fileManager.save(new FileSaveDto(orginImgFileName, File.separator + "image", file));

        UUID imageUuid = UUID.randomUUID();
        Long imageSeq = markerImageRepository.saveAndFlush(MarkerImage.builder()
                        .id(imageUuid)
                        .originImage(thumbnailUrl)
                        .thumbnail(originImageUrl)
                        .build())
                .getSeq();

        MarkerDto markerDto = new MarkerDto(reqDto, imageSeq);
        modelMapper.map(markerDto, findMarker);

        return new BaseResponse(StatusEnum.OK, StatusEnum.OK.getCode(), seq);
    }

    private String makeFilePrefixName(UUID markerId){
        return markerId + "_" + DateUtil.getYyyyMmDd(LocalDateTime.now()) + "_";
    }

}
