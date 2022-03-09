package com.onion.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="customer")
@Data

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_customer;
    private double x;
    private double y;
    private int demand;

    public Customer(int id_customer, double x, double y, int demand) {
        this.id_customer = id_customer;
        this.x = x;
        this.y = y;
        this.demand = demand;
    }

    public Customer() {

    }
}
