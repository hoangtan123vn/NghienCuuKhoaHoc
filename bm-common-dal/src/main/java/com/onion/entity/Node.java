package com.onion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="node")
public class Node{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idnode;

    @JsonProperty("address")
    public String address;
    @JsonProperty("lat")
    public double lat;
    @JsonProperty("lng")
    public double lng;
    @JsonProperty("demand")
    public int demand;


    @Column(name="routed")
    public boolean isRouted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_route")
    @JsonIgnore
    public HistoryRoutes historyRoutes;

    public Node() {

    }
    public Node(int idnode,String address,double lat,double lng,int demand){
        this.idnode = idnode;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.demand = demand;
    }
}
