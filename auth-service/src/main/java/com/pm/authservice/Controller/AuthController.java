package com.pm.authservice.Controller;

import com.pm.authservice.Service.AuthService;
import com.pm.authservice.dto.LoginRequestDto;
import com.pm.authservice.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    public AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<String> tokenres=authService.authenticate(loginRequestDto);
        System.out.println(tokenres);
        String token=tokenres.get();
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authheader){
        if(authheader ==null || !authheader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return authService.validateToken(authheader.substring(7))?ResponseEntity.ok().build():ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
