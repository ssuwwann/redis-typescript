package com.suwan.redis.entitiy.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductFileCommand {
  private final FileInfomation descriptionImage;
  private final List<FileInfomation> productImages;
}
