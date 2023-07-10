package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class EmployeeSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder)
    {
        UserDetails admin = User.withUsername("karthi")
                .password(encoder.encode("reset@123"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("yaazhini")
                .password(encoder.encode("Reset@123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/employee/welcome").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/employee/**")
                .authenticated()
                .and().formLogin().and().build();
    }


}
