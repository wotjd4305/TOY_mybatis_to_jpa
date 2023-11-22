package com.potatoes.potential.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileSaveDto {

    private String fileName = "";
    private String subPath = "";
    private MultipartFile multipartFile;
}
