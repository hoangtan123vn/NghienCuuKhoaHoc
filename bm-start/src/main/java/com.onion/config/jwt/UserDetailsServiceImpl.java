package com.onion.config.jwt;

import com.onion.entity.Role;
import com.onion.entity.User;
import com.onion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserPrincipal.create(user);
    }

    @Transactional
    // Get user by id
    public UserDetails loadUserById(Long id) {
        User account = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id " + id));
        return UserPrincipal.create(account);
    }

    public User save(User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setId_role(1);
        role.setName_role("ADMIN");
        newUser.setRole(role);
        return userRepository.save(newUser);
    }



}
