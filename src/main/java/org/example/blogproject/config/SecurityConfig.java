package org.example.blogproject.config;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations()
                );
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/minlog", "/minlog/userregform", "minlog/userreg", "/minlog/viewpost/**", "/minlog/profile/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/minlog/loginform")
                        .loginProcessingUrl("/minlog/login")
                        .defaultSuccessUrl("/minlog")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/minlog")
                )
                .userDetailsService(customUserDetailsService)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
