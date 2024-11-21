package com.suwan.redis.entitiy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateToken {

  private String newAccessToken;
  private String newRefreshToken;

}
