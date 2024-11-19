package com.suwan.redis.controller.member;

import com.suwan.redis.entitiy.user.dto.UserRequest;
import com.suwan.redis.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> saveUser(@RequestBody UserRequest dto) {
    log.info("Saving user: {}", dto);
    userService.saveUser(dto);
    return ResponseEntity.ok().build();
  }

}
