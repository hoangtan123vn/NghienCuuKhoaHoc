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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_vehicle;

    public long cost;

    public int loading;

    public boolean status;


    @Transient
    public ArrayList<Node> nodes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_type")
    private VehicleType vehicleType;


    @Override
    public String toString() {
        return "Vehicle{" +
                "id_vehicle=" + id_vehicle +
                ", cost=" + cost +
                ", loading=" + loading +
                ", status=" + status +
                ", nodes=" + nodes +
                ", vehicleType=" + vehicleType +
                '}';
    }

    public Vehicle(int cost , int loading){
        this.cost = cost;
        this.loading = loading;
    }

    public Vehicle(int id_vehicle, int cost ,int loading){
        this.id_vehicle = id_vehicle;
        this.cost = cost;
        this.loading = loading;
    }

    public Vehicle(int id_vehicle, int cost ,int loading,boolean status){
        this.id_vehicle = id_vehicle;
        this.cost = cost;
        this.loading = loading;
        this.status = status;
    }

    public Vehicle(int cost ,boolean status){
        this.cost = cost;
        this.status = status;
    }

}
