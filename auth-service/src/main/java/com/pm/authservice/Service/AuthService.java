package com.pm.authservice.Service;

import com.pm.authservice.Util.JwtUtil;
import com.pm.authservice.dto.LoginRequestDto;
import com.pm.authservice.model.User;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    public UserService userService;

    public JwtUtil jwtUtil;

    public PasswordEncoder passwordEncoder;

    public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        Optional<String> token= userService.findByEmail(loginRequestDto.getEmail())
                .filter(pwd-> passwordEncoder.matches(loginRequestDto.getPassword(), pwd.getPassword()))
                .map(u->jwtUtil.generateToken(u.getEmail(),u.getRole()));
        System.out.println(token);
        System.out.println(loginRequestDto.getEmail());
        System.out.println(loginRequestDto.getPassword());
        System.out.println(userService.findByEmail(loginRequestDto.getEmail()));
            return token;
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
