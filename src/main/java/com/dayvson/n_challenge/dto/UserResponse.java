package com.dayvson.n_challenge.dto;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String nationalId
) {}
