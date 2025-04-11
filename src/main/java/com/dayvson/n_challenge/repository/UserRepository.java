package com.dayvson.n_challenge.repository;

import com.dayvson.n_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByNationalId(String nationalId);
}
