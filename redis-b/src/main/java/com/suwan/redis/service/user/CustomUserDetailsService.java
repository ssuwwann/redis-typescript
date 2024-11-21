package com.suwan.redis.service.user;

import com.suwan.redis.common.Cookies;
import com.suwan.redis.entitiy.user.User;
import com.suwan.redis.entitiy.user.dto.CustomUserDetails;
import com.suwan.redis.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final Cookies cookies;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("AUTH001"));

    return new CustomUserDetails(user);
  }
}
