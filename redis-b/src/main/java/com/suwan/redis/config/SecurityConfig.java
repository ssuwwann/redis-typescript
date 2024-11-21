package com.suwan.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suwan.redis.common.Cookies;
import com.suwan.redis.jwt.JwtFilter;
import com.suwan.redis.jwt.JwtUtil;
import com.suwan.redis.service.user.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final RefreshService refreshService;
  private final AuthenticationConfiguration authenticationConfiguration;
  private final ObjectMapper objectMapper;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;
  private final Cookies cookies;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
    http.formLogin(formLogin -> formLogin.disable());
    http.sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.httpBasic(basic -> basic.disable());

    http.authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll());

    http.addFilterAt(new CustomLoginFilter(refreshService, authenticationManager(authenticationConfiguration), objectMapper, jwtUtil, cookies), UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(new JwtFilter(cookies, jwtUtil, userDetailsService), CustomLoginFilter.class);
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:5173");
    config.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    config.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return source;
  }


  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
