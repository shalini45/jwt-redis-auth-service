package com.authservice.Controller;

import com.authservice.Repository.UserRepository;
import com.authservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserRepository userRepository;

    // ─── Get own profile (USER or ADMIN) ─────────────────────
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getProfile(
            Authentication authentication) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow();

        return ResponseEntity.ok(Map.of(
            "id", user.getId(),
            "username", user.getUsername(),
            "email", user.getEmail(),
            "role", user.getRole(),
            "createdAt", user.getCreatedAt().toString()
        ));
    }

    // ─── Change password (USER or ADMIN) ─────────────────────
    @PutMapping("/change-password")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Map<String, String>> changePassword(
            Authentication authentication,
            @RequestBody Map<String, String> request) {

        // We will implement this later
        return ResponseEntity.ok(Map.of(
            "message", "Password change coming soon"
        ));
    }
}