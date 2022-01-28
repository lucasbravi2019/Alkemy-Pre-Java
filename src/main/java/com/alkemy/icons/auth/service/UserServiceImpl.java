package com.alkemy.icons.auth.service;

import com.alkemy.icons.auth.dto.TokenDTO;
import com.alkemy.icons.auth.dto.UserDTO;
import com.alkemy.icons.auth.entity.UserEntity;
import com.alkemy.icons.auth.repo.UserRepo;
import com.alkemy.icons.auth.util.JWTUtils;
import com.alkemy.icons.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public UserEntity getUser(String username) {
        return userRepo.findByUsername(username).get();
    }

    @Override
    public UserEntity getUser(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public UserDTO saveUser(UserDTO dto) throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setAccountNonBlocked(dto.getAccountNonBlocked());
        userEntity.setAccountIsActive(dto.getAccountIsActive());
        userEntity.setCredentialsNonExpired(dto.getCredentialsNonExpired());
        userEntity = userRepo.save(userEntity);
        if(userEntity != null) {
            emailService.sendEmail(userEntity.getUsername());
        }
        return dto;
    }

    @Override
    public TokenDTO login(AuthenticationManager authenticationManager, UserDTO dto) {
        UserDetails userDetails;
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        userDetails = (UserDetails) auth.getPrincipal();
        String accessToken = JWTUtils.createAccessToken(userDetails);
        String refreshToken = JWTUtils.createRefreshToken(userDetails);
        return new TokenDTO(accessToken, refreshToken);
    }

    @Override
    public TokenDTO refreshToken(HttpServletRequest request) throws Exception {
        String authenticationHeader = request.getHeader("Authorization");
        if(authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            String refreshToken = authenticationHeader.substring("Bearer ".length());
            String username = JWTUtils.decodeJWT(refreshToken);
            UserEntity user = getUser(username);
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String accessToken = JWTUtils.createAccessToken(user);
            return new TokenDTO(accessToken, refreshToken);
        } else {
            throw new Exception();
        }
    }

}
