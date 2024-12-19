package com.suwan.redis.util;

import com.suwan.redis.domain.file.FileType;
import com.suwan.redis.domain.file.ProductFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUpload {

  @Value("${file.upload.base-path}")
  private String basePath;

  public String uploadFile(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) return null;

    String datePath = createDatePath();
    File uploadDir = new File(basePath + datePath);

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

  public ResponseEntity<Resource> getThubnail(List<ProductFile> productFiles) {
    ProductFile productFile = productFiles.stream()
            .filter(pf -> pf.getFileType() == FileType.PRODUCT_IMAGE)
            .findFirst().orElseThrow();
    Path path = Paths.get(basePath, productFile.getSavedPath());

    Resource fileSystemResource = new FileSystemResource(path);

    if (!fileSystemResource.exists()) return null;

    try {
      String contentType = Files.probeContentType(path);

      return ResponseEntity.ok()
              .contentType(MediaType.parseMediaType(contentType))
              .body(fileSystemResource);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }


  private String createDatePath() {
    LocalDateTime now = LocalDateTime.now();
    return String.format(File.separator + "%d" + File.separator + "%02d" + File.separator + "%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
  }

}
