package com.onion.config.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onion.entity.User;
import com.onion.entity.Vehicle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {

    private int id;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    private String fullname;

    private int age;

    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    private Vehicle vehicle;

    private User user;
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public UserPrincipal(int id, String username, String password, String fullname , int age , String address, Collection<? extends GrantedAuthority> authorities , Vehicle vehicle) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
        this.address = address;
        this.authorities = authorities;
        this.vehicle = vehicle;
    }

    public UserPrincipal( String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserPrincipal() {
    }

    public static UserPrincipal create(User user) {
        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getName_role());
        return new UserPrincipal(Math.toIntExact(user.getIduser()), user.getUsername(), user.getPassword() , user.getFullname(),user.getAge(), user.getAddress(), Collections.singleton(authorities), user.getVehicle());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }
}