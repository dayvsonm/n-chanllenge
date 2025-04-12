package com.dayvson.n_challenge.service;

import com.dayvson.n_challenge.dto.UserRequest;
import com.dayvson.n_challenge.dto.UserResponse;
import com.dayvson.n_challenge.mapper.UserMapper;
import com.dayvson.n_challenge.model.User;
import com.dayvson.n_challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponse create(UserRequest request) {
        if(repository.existsByEmail(request.email())){
            throw new IllegalArgumentException(("Email already exists"));
        }

        if(repository.existsByNationalId(request.nationalId())){
            throw new IllegalArgumentException("National Id already exists");
        }

        User user = UserMapper.toEntity(request);

        user = repository.save(user);

        return UserMapper.toResponse(user);
    }


    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(UserMapper::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return UserMapper.toResponse(user);
    }

    public UserResponse update (Long id, UserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setNationalId(request.nationalId());
        user.setPassword(request.password());

        return UserMapper.toResponse(repository.save(user));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw  new EntityNotFoundException("User not found");
        }
        repository.deleteById(id);
    }

}
