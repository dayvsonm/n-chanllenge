package com.dayvson.n_challenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @Size(min = 5, max = 20) @NotBlank String nationalId,
        @NotBlank String password
) {}
