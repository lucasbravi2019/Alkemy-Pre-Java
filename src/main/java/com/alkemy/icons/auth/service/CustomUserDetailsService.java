package com.alkemy.icons.auth.service;

import com.alkemy.icons.auth.dto.TokenDTO;
import com.alkemy.icons.auth.dto.UserDTO;
import com.alkemy.icons.auth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO dto) throws IOException {
        return userServiceImpl.saveUser(dto);
    }

    public TokenDTO login(AuthenticationManager authenticationManager, UserDTO dto) {
        return userServiceImpl.login(authenticationManager, dto);
    }

    public TokenDTO refreshToken(HttpServletRequest request) throws Exception {
        return userServiceImpl.refreshToken(request);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userServiceImpl.getUser(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

}
