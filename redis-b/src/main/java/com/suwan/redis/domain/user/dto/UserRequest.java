package com.suwan.redis.domain.user.dto;

public record UserRequest(String email, String password, String username, String address) {
}