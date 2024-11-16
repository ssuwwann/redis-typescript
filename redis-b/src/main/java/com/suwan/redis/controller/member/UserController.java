package com.suwan.redis.controller.member;

import com.suwan.redis.entitiy.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  @PostMapping
  public ResponseEntity<Void> saveUser(@RequestBody UserRequest dto) {

    return ResponseEntity.ok().build();
  }

}
