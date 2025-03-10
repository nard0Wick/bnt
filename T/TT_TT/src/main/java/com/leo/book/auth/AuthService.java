package com.leo.book.auth;

import com.leo.book.dto.AuthenticationRequest;
import com.leo.book.dto.RegRequest;
import com.leo.book.jwt.JwtService;
import com.leo.book.user.Role;
import com.leo.book.user.User;
import com.leo.book.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegRequest regRequest){
        if (!userRepo.existsByEmail(regRequest.getEmail())){
            User user = User.builder()
                    .email(regRequest.getEmail())
                    .password(passwordEncoder.encode(regRequest.getPassword()))
                    .firstName(regRequest.getFirstName())
                    .lastName(regRequest.getLastName())
                    .role(Role.USER)
                    .build();
            userRepo.save(user);
        }
    }

    public String login(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );
        User user = userRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow(RuntimeException::new);
        return jwtService.generateToken(user);
    }

}
