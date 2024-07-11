package org.example.blogproject.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.domain.Role;
import org.example.blogproject.domain.User;
import org.example.blogproject.repository.RoleRepository;
import org.example.blogproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //user create
    @Transactional
    public User createUser(User user){
        /*
        if(userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("Username already exists.");
        }
        */
        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(roleUser));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Transactional
    public boolean isExistUsername(String username){
        return userRepository.existsByUsername(username);
    }
    @Transactional
    public User getUser(String username){
        return userRepository.findByUsername(username);
    }
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
