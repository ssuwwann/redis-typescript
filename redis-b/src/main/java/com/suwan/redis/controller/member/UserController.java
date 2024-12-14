package com.suwan.redis.controller.member;

import com.suwan.redis.entitiy.user.dto.CustomUserDetails;
import com.suwan.redis.entitiy.user.dto.UserRequest;
import com.suwan.redis.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<String> addUser(@RequestBody UserRequest dto) {
    String username = userService.saveUser(dto);
    return ResponseEntity.ok(username);
  }

  @PostMapping("/roles")
  public ResponseEntity<String> getRole(Authentication authentication) {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
    String authority = user.getAuthorities().iterator().next().getAuthority();

    return ResponseEntity.ok(authority);
  }

  @GetMapping("/seller")
  public ResponseEntity<String> applySeller(Authentication authentication) {
    userService.registerSeller(authentication);
    return ResponseEntity.ok("SE");
  }

}
