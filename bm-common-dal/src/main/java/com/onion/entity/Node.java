package com.onion.entity;

import lombok.Data;



@Data
public class Node{

    public int id_customer;
    public String address;
    public double lat;
    public double lng;
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
