package com.pmyo.pmyo.controller;

import com.pmyo.pmyo.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
public class UserController {
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String name, HttpSession session) {
        User user = new User(name);
        session.setAttribute("user", user);
        return ResponseEntity.ok().body("User '" + name + "' logged in successfully.");
    }

    @GetMapping("/home")
    public ResponseEntity<?> homePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok().body("Welcome, " + user.getName());
        }
        return ResponseEntity.status(401).body("User is not logged in.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().body("User logged out successfully.");
    }
}