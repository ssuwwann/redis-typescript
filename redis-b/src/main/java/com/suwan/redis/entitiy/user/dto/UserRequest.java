package com.suwan.redis.entitiy.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {

  private String email;
  private String username;
  private String password;
  private String address;
  private String profileImage;
}
