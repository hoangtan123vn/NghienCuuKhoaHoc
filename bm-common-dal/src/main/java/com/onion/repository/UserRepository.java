package com.onion.repository;


import com.onion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);

    public boolean existsByUsername(String username);

    public boolean existsByIduser(Long iduser);
}