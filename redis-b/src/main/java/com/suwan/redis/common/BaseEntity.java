package com.suwan.redis.common;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Comment("등록 일자")
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @Comment("수정 일자")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Comment("작성자")
  @Column(nullable = false)
  @CreatedBy
  private String writer;

  @Comment("작성자")
  @LastModifiedBy
  private String updater;

  @Comment("0 = 활동, 1 = 삭제")
  @Column(nullable = false)
  private boolean isDeleted = false;
}
