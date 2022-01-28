package com.alkemy.icons.auth.controller;


import com.alkemy.icons.auth.dto.TokenDTO;
import com.alkemy.icons.auth.dto.UserDTO;
import com.alkemy.icons.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO dto) {
        try {
            return new ResponseEntity<>(customUserDetailsService.register(dto), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody UserDTO dto) {
        try {
            return new ResponseEntity<>(customUserDetailsService.login(authenticationManager, dto), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshToken(HttpServletRequest request) throws IOException {
        try {
            return new ResponseEntity<>(customUserDetailsService.refreshToken(request), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
