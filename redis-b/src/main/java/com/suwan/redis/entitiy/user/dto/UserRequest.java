package com.suwan.redis.entitiy.user.dto;

public record UserRequest(String email, String password, String username, String address) {
}