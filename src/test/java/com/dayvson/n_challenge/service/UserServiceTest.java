package com.dayvson.n_challenge.service;

import com.dayvson.n_challenge.dto.UserRequest;
import com.dayvson.n_challenge.model.User;
import com.dayvson.n_challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        UserRequest request = buildRequest();
        User saved = new User(1L, request.fullName(), request.email(), request.nationalId(), request.password());

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByNationalId(request.nationalId())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(saved);

        var response = userService.create(request);

        assertNotNull(response);
        assertEquals("dayvson@email.com", response.email());
        assertEquals("Dayvson", response.fullName());
        verify(userRepository).save(any());
    }

    @Test
    void shouldThrowIfEmailAlreadyExists() {
        UserRequest request = buildRequest();
        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        var ex = assertThrows(IllegalArgumentException.class, () -> userService.create(request));
        assertEquals("Email already exists", ex.getMessage());
    }

    @Test
    void shouldThrowIfNationalIdAlreadyExists() {
        UserRequest request = buildRequest();
        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.existsByNationalId(request.nationalId())).thenReturn(true);

        var ex = assertThrows(IllegalArgumentException.class, () -> userService.create(request));
        assertEquals("National Id already exists", ex.getMessage());
    }

    @Test
    void shouldReturnUserById() {
        User user = new User(1L, "Dayvson", "dayvson@email.com", "123456789EU", "123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userService.findById(1L);
        assertEquals("Dayvson", result.fullName());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(99L));
    }

    private UserRequest buildRequest() {
        UserRequest request = new UserRequest("Dayvson", "dayvson@email.com"
        , "123456789EU", "123456");
        return request;
    }
}
