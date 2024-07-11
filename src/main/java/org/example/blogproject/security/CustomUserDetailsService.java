package org.example.blogproject.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blogproject.domain.User;
import org.example.blogproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(":::::CustomUserDetailsService1:::::");
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }
        log.info(":::::CustomUserDetailsService2:::::");
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles(user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new));

        log.info(":::::CustomUserDetailsService3:::::");
        return userBuilder.build();
    }
}
