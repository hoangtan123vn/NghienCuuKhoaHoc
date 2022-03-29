package com.onion.controller;

import com.onion.config.jwt.UserDetailsServiceImpl;
import com.onion.UserService;
import com.onion.UserServiceImpl;
import com.onion.config.jwt.JwtTokenProvider;
import com.onion.entity.User;
import com.onion.repository.UserRepository;
import com.onion.request.LoginRequest;
import com.onion.respone.JwtAuthenticationRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/auth")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    private final UserDetailsServiceImpl userDetailsServiceimpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public User Register(@RequestBody User user){
        return userDetailsServiceimpl.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
         /* Authentication authentication =*/   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
          //  SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsServiceimpl
                .loadUserByUsername(loginRequest.getUsername());


        final String token = jwtTokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationRespone(token));
    }

    
    @GetMapping("/admin")
    public String forAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String forUser(){
        return "Hello User";
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
   /* public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }*/


}