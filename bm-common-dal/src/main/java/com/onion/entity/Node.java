package com.onion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Node{

    public int id_customer;

    @JsonProperty("address")
    public String address;
    @JsonProperty("lat")
    public double lat;
    @JsonProperty("lng")
    public double lng;
    @JsonProperty("demand")
    public int demand;
    public boolean isRouted;
    public Node() {

    }
    public Node(int id_customer,String address,double lat,double lng,int demand){
        this.id_customer = id_customer;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.demand = demand;
    }
}
