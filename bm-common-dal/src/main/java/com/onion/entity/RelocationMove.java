package com.onion.entity;

public class RelocationMove {
    private int positionOfRelocated;
    private int positionToBeInserted;
    private int route;
    private double moveCost;
    private int fromRoute;
    private int toRoute;
    private double moveCostTo;
    private double moveCostFrom;
    private int newLoadFrom;
    private int newLoadTo;
    public RelocationMove(int positionOfRelocated, int positionToBeInserted, int route, double moveCost) {
        this.positionOfRelocated = positionOfRelocated;
        this.positionToBeInserted = positionToBeInserted;
        this.route = route;
        this.moveCost = moveCost;
    }

    public RelocationMove(int positionOfRelocated, int positionToBeInserted, int fromRoute, int toRoute, double moveCostFrom, double moveCostTo) {
        this.positionOfRelocated = positionOfRelocated;
        this.positionToBeInserted = positionToBeInserted;
        this.fromRoute = fromRoute;
        this.toRoute = toRoute;
        this.moveCostFrom = moveCostFrom;
        this.moveCostTo = moveCostTo;
    }

    public int getPositionOfRelocated() {
        return positionOfRelocated;
    }

    public int getPositionToBeInserted() {
        return positionToBeInserted;
    }

    public int getRoute() {
        return route;
    }

    public double getMoveCost() {
        return moveCost;
    }

    public void setPositionOfRelocated(int positionOfRelocated) {
        this.positionOfRelocated = positionOfRelocated;
    }

    public void setPositionToBeInserted(int positionToBeInserted) {
        this.positionToBeInserted = positionToBeInserted;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public void setMoveCost(double moveCost) {
        this.moveCost = moveCost;
    }

    public int getFromRoute() {
        return fromRoute;
    }

    public void setFromRoute(int fromRoute) {
        this.fromRoute = fromRoute;
    }

    public int getToRoute() {
        return toRoute;
    }

    public void setToRoute(int toRoute) {
        this.toRoute = toRoute;
    }

    public double getMoveCostTo() {
        return moveCostTo;
    }

    public void setMoveCostTo(double moveCostTo) {
        this.moveCostTo = moveCostTo;
    }

    public double getMoveCostFrom() {
        return moveCostFrom;
    }

    public void setMoveCostFrom(double moveCostFrom) {
        this.moveCostFrom = moveCostFrom;
    }

    public int getNewLoadFrom() {
        return newLoadFrom;
    }

    public void setNewLoadFrom(int newLoadFrom) {
        this.newLoadFrom = newLoadFrom;
    }

    public int getNewLoadTo() {
        return newLoadTo;
    }

    public void setNewLoadTo(int newLoadTo) {
        this.newLoadTo = newLoadTo;
    }


}
