package com.dayvson.n_challenge.mapper;

import com.dayvson.n_challenge.dto.UserRequest;
import com.dayvson.n_challenge.dto.UserResponse;
import com.dayvson.n_challenge.model.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        return new User(
                null,
                request.fullName(),
                request.email(),
                request.nationalId(),
                request.password()
        );
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getNationalId()
        );
    }
}
