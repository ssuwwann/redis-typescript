package com.suwan.redis.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUpload {

  private static final String BASE_PATH = "C:/redis";

  public String uploadFile(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) return null;

    String datePath = createDatePath();
    File uploadDir = new File(BASE_PATH + datePath);

    if (!uploadDir.exists()) uploadDir.mkdirs();

    String originalFilename = file.getOriginalFilename();
    String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
    String savedFileName = UUID.randomUUID().toString().substring(0, 7) + extention;

    File destFile = new File(uploadDir.getAbsolutePath() + File.separator + savedFileName);
    file.transferTo(destFile);

    return datePath + File.separator + savedFileName;
  }

  public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
    ArrayList<String> uploadedPaths = new ArrayList();

    if (files != null) {
      for (MultipartFile file : files) {
        String path = uploadFile(file);
        if (path != null) uploadedPaths.add(path);
      }
    }

    return uploadedPaths;
  }

  private String createDatePath() {
    LocalDateTime now = LocalDateTime.now();
    return String.format("/%d/%02d/%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
  }

}
