package com.suwan.redis.config;

import com.suwan.redis.entitiy.user.User;
import com.suwan.redis.entitiy.user.dto.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginMemberAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) return null;

    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
    return Optional.of(user.getId());
  }
}
