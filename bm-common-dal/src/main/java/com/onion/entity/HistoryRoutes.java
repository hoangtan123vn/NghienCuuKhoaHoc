package com.onion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="historyroutes")
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_route;

    public Date time;

    public long cost_route;

    public int loading_route;

    public int capacity_route;

    public boolean status_route;


    @ManyToOne
    @JoinColumn(name="id_vehicle")
    public Vehicle vehicle;


    @OneToMany(mappedBy = "historyRoutes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<Node> nodes;



}



