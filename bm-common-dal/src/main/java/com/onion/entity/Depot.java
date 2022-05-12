package com.onion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="depot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  int id_depot;
    public  double lat;
    public  double lng;
    public  String address;
    public  int demand;
}
