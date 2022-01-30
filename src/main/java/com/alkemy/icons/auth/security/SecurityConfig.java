package com.alkemy.icons.auth.security;

import com.alkemy.icons.auth.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers("/auth/signin").permitAll();
        http.authorizeHttpRequests().antMatchers("/auth/signup").permitAll();
        http.authorizeHttpRequests().antMatchers("/auth/refresh").permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/images/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/swagger-ui/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/swagger-resources/**").permitAll();
        http.authorizeHttpRequests().antMatchers("/v3/**").permitAll();
        http.authorizeHttpRequests().antMatchers("**").authenticated();
        http.addFilterBefore(new CustomAuthorizationFilter(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
    }
}
