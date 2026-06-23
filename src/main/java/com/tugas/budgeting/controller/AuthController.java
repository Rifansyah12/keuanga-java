package com.tugas.budgeting.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// Poin Plus: Login sederhana dengan validasi dari application.properties
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${app.username:admin}")
    private String appUsername;

    @Value("${app.password:admin123}")
    private String appPassword;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (appUsername.equals(username) && appPassword.equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login berhasil",
                    "username", username
            ));
        }
        return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Username atau password salah"
        ));
    }
}
