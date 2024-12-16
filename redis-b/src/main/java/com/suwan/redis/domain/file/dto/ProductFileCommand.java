package com.suwan.redis.domain.file.dto;

import java.util.List;

public record ProductFileCommand(FileInfomation descriptionImage, List<FileInfomation> productImages) {
}
