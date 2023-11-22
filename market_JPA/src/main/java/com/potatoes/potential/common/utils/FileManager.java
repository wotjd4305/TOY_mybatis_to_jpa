package com.potatoes.potential.common.utils;

import com.potatoes.potential.common.dto.FileSaveDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

@Configuration
public class FileManager {

    @Value("${file.basePath}")
    private String basePath;

    private boolean createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return false;
    }

    private String saveFile(String path, MultipartFile multipartFile) {
        if (multipartFile != null) {
            File file = new File(path);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            return path;
        }
        return "";
    }

    // 이미지 크기 줄이기
    private BufferedImage resizeImageFile(MultipartFile file) throws Exception {
        BufferedImage inputImage = ImageIO.read(file.getInputStream());
        int originWidth = inputImage.getWidth();
        int originHeight = inputImage.getHeight();
        int newWidth = 500;

        if (originWidth > newWidth) {
            int newHeight = (originHeight * newWidth) / originWidth;
            Image resizeImage = inputImage.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = newImage.getGraphics();
            graphics.drawImage(resizeImage, 0, 0, null);
            graphics.dispose();

            return newImage;
        } else {
            return inputImage;
        }
    }

    public String saveResizedFile(FileSaveDto file, String formatName) {
        String filePathDir = basePath + file.getSubPath() + File.separator;
        String filePath = filePathDir + file.getFileName();
        createDir(filePathDir);

        try {
            BufferedImage bufferedImage = resizeImageFile(file.getMultipartFile());
            File newFile = new File(filePath);
            ImageIO.write(bufferedImage, formatName, newFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        ;
        return filePath;
    }

    public String save(FileSaveDto file) {
        String filePath = basePath + file.getSubPath() + File.separator;
        createDir(filePath);
        return saveFile(filePath + file.getFileName(), file.getMultipartFile());
    }

}
