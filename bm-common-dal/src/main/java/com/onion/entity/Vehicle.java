package com.onion.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="vehicle")
public class Vehicle {

    @Id
    public int id_vehicle;

    public int capacity;

    public long cost;

    public int loading;

    @Transient
    public ArrayList<Node> nodes = new ArrayList<>();

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

    public Vehicle(int id_vehicle,int capacity, int cost ,int loading){
        this.id_vehicle = id_vehicle;
        this.capacity = capacity;
        this.cost = cost;
        this.loading = loading;
    }





    public boolean CheckIfFits(int dem) //Check if we have Capacity Violation
    {
        return ((loading + dem <= capacity));
    }

}
