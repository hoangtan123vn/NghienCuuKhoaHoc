package com.onion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("lat")
    public  double lat;
    @JsonProperty("lng")
    public  double lng;
    @JsonProperty("address")
    public  String address;
    @JsonProperty("demand")
    public  int demand;
}
