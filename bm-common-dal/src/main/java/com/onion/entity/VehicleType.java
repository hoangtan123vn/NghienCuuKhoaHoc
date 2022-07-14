package com.onion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="vehicletype")
@AllArgsConstructor
@NoArgsConstructor
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_type;
    @JsonProperty
    public String name_type;
    @JsonProperty
    public int capacity;

}
