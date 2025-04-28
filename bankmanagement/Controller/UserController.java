package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/my-info")
    public ResponseEntity getMyInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getAllUsers(user.getId()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteMyAccount(@AuthenticationPrincipal User user) {
        userService.deleteUser(user.getId());
        return ResponseEntity.ok("Your account has been deleted successfully");
    }
}
