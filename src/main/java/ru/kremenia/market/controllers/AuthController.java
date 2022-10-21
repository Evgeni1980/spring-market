package ru.kremenia.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kremenia.market.dtos.JwtRequest;
import ru.kremenia.market.dtos.JwtResponse;
import ru.kremenia.market.service.UserService;
import ru.kremenia.market.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {
    // userService он знает, где лежит пользователь
    private final UserService userService;
    // JwtTokenUtil утилита для работы с токенами
    private final JwtTokenUtil jwtTokenUtil;
    // authenticationManager спринговый бин который занимается аутентификациеей
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}