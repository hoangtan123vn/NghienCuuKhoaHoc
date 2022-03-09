package com.onion.entity;

import java.util.ArrayList;

public class Solution {
    private double cost;
    private ArrayList<Route> route;
    public Solution() {
        this.route = new ArrayList<>();
        this.cost = 0;
    }

    public static Solution cloneSolution(Solution solution) {
        Solution out = new Solution();
        out.setCost(solution.getCost());
        out.setRoute((ArrayList<Route>) solution.getRoute().clone());
        return out;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double costs) {
        this.cost = costs;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    public ArrayList<Route> getRoute() {
        return route;
    }
}
