package com.dayvson.n_challenge.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String nationalId;
}
