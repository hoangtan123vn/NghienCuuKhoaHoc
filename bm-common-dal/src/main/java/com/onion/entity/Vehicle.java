package com.onion.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.lang.reflect.Type;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_vehicle;

    public int capacity;

    public int cost;

    public int loading;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id_vehicle=" + id_vehicle +
                ", capacity=" + capacity +
                ", cost=" + cost +
                ", loading=" + loading +
                '}';
    }

    public Vehicle(int capacity, int cost ,int loading){
        this.capacity = capacity;
        this.cost = cost;
        this.loading = loading;
    }

}
