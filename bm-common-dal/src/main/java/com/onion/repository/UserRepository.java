package com.onion.repository;


import com.onion.entity.User;
import com.onion.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);
    Optional<User> findByUsername(String username);
    User findByVehicle(Vehicle vehicle);
}