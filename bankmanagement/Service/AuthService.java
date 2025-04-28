package com.example.bankmanagement.Service;

import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    public void register(User user) {
        user.setRole("USER");

        String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);


        authRepository.save(user);
    }

    public User findByUsername(String username) {
        return authRepository.findUserByUsername(username);
    }
}
