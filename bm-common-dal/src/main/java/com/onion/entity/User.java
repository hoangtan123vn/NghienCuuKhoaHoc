package com.onion.entity;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;


}
