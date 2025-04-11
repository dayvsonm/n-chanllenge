package com.dayvson.n_challenge.service;

import com.dayvson.n_challenge.dto.UserRequest;
import com.dayvson.n_challenge.dto.UserResponse;
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
        if(repository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException(("Email already exists"));
        }

        if(repository.existsByNationalId(request.getNationalId())){
            throw new IllegalArgumentException("National Id already exists");
        }

        User user = new User(null, request.getFullName(), request.getEmail(), request.getNationalId(), request.getPassword());

        user = repository.save(user);

        return toResponse(user);
    }


    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return toResponse(user);
    }

    public UserResponse update (Long id, UserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setNationalId(request.getNationalId());
        user.setPassword(request.getPassword());

        return toResponse(repository.save(user));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw  new EntityNotFoundException("User not found");
        }
        repository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setNationalId(user.getNationalId());
        return response;
    }
}
