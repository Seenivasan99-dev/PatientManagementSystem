package com.pm.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDto {
    @Email(message = "Email should be a valid email address")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min=8,message = "password must be min 8 characters")
    private String password;

    public @Email(message = "Email should be a valid email address") @NotBlank(message = "Email is Required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be a valid email address") @NotBlank(message = "Email is Required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is Required") @Size(min = 8, message = "password must be min 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is Required") @Size(min = 8, message = "password must be min 8 characters") String password) {
        this.password = password;
    }
}
