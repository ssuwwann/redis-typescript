package com.suwan.redis.domain.user.dto;

public record UpdateToken(String newAccessToken, String newRefreshToken) {
}
