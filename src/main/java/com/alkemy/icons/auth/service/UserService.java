package com.alkemy.icons.auth.service;

import com.alkemy.icons.auth.dto.TokenDTO;
import com.alkemy.icons.auth.dto.UserDTO;
import com.alkemy.icons.auth.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface UserService {

    UserEntity getUser(String username);
    UserEntity getUser(Long id);
    UserDTO saveUser(UserDTO dto) throws IOException;
    TokenDTO login(AuthenticationManager authenticationManager, UserDTO dto);
    TokenDTO refreshToken(HttpServletRequest request) throws Exception;

}
