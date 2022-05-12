package com.onion.entity;

import java.util.ArrayList;

public class Solution {
    public double cost;
    public ArrayList<Vehicle> vehicles;
    public Solution() {
        this.vehicles = new ArrayList<>();
        this.cost = 0;
    }

    public static Solution cloneSolution(Solution solution) {
        Solution out = new Solution();
        out.setCost(solution.getCost());
        out.setRoute((ArrayList<Vehicle>) solution.getRoute().clone());
        return out;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double costs) {
        this.cost = costs;
    }

    public void setRoute(ArrayList<Vehicle> vehicle) {
        this.vehicles = vehicle;
    }

    public ArrayList<Vehicle> getRoute() {
        return vehicles;
    }
}
