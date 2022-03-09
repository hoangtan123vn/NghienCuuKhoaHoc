package com.onion.entity;

import java.util.ArrayList;

public class Route {
    private ArrayList<Customer> customers;
    private double cost;
    private int idroute;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setRoutes(ArrayList<Customer> routes) {
        this.customers = routes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getIdroute() {
        return idroute;
    }

    public void setIdroute(int idroute) {
        this.idroute = idroute;
    }
}
