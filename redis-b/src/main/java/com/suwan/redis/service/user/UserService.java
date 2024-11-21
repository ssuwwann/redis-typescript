package com.suwan.redis.service.user;

import com.suwan.redis.entitiy.cart.Cart;
import com.suwan.redis.entitiy.user.User;
import com.suwan.redis.entitiy.user.dto.UserRequest;
import com.suwan.redis.repository.cart.CartRepository;
import com.suwan.redis.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final PasswordEncoder passwordEncoder;

  public String saveUser(UserRequest dto) {
    Cart cart = cartRepository.save(Cart.createEmpty());
    User user = userRepository.save(User.from(dto, cart));
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    cart.setWriter(user.getId());
    cart.setUpdater(user.getId());
    user.setWriter(user.getId());
    user.setUpdater(user.getId());

    return user.getUsername();
  }

}
