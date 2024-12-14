package com.suwan.redis.entitiy.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Builder
@AllArgsConstructor
public class FileInfomation {
  private String originalFilename;  // 원본 파일명
  private String savedFilename;     // 저장된 파일명
  private String savedPath;         // 저장 경로
  private String extension;         // 파일 확장자
  private Long fileSize;           // 파일 크기

  public static FileInfomation of(MultipartFile file, String savedPath) {
    return FileInfomation.builder()
            .originalFilename(file.getOriginalFilename())
            .savedFilename(extractFilename(savedPath))
            .savedPath(savedPath)
            .extension(extractExtention(file.getOriginalFilename()))
            .fileSize(file.getSize())
            .build();
  }

  private static String extractExtention(String originalFilename) {
    return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
  }

  private static String extractFilename(String savedPath) {
    return savedPath.substring(savedPath.lastIndexOf(File.separator) + 1);
  }

}
