package com.example.bankmanagement.Service;

import com.example.bankmanagement.Api.ApiException;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(Integer userId) {
        User user=userRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        return userRepository.findAll();
    }

    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException("User not found"));
        userRepository.delete(user);
    }
}
