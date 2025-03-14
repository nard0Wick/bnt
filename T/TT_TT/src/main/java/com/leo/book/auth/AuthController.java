package com.leo.book.auth;

import com.leo.book.dto.AuthenticationRequest;
import com.leo.book.dto.RegRequest;
import com.leo.book.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<Object>  registerUser(@RequestBody RegRequest regRequest) throws MessagingException {
        authService.register(regRequest);
        emailService.sendEmail(regRequest.getEmail(), "Don't replay", "activate_account");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }
}
