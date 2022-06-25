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

    private String fullname;

    private int age;

    private String address;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "id_vehicle")
    private Vehicle vehicle;


    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public User(String username,String password,String fullname,int age,String address){
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", vehicle=" + vehicle +
                '}';
    }
}
