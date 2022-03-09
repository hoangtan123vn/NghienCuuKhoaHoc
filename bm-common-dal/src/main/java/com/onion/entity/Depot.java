package com.onion.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="depot")
@Data
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id_depot;
    private  int x;
    private  int y;
    private  int demand;

}
