package com.dayvson.n_challenge.config;

import com.dayvson.n_challenge.model.User;
import com.dayvson.n_challenge.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner loadInitialUsers(UserRepository userRepository){
        return args -> {
            if (userRepository.count() == 0) {
                User user = new User(
                        null,
                        "Dayvson",
                        "dayvson@example.com",
                        "123456789EU",
                        "1234safe"
                );
                userRepository.save(user);
            }
        };
    }
}
