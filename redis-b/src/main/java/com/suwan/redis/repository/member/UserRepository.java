package com.suwan.redis.repository.member;

import com.suwan.redis.entitiy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
