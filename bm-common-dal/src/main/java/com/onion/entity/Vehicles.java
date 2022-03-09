package com.onion.entity;

public class Vehicles {
    private int id;
    private Double[] start;
    private Double[] end;
    private String vehicle_types;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double[] getStart() {
        return start;
    }

    public void setStart(Double[] start) {
        this.start = start;
    }

    public Double[] getEnd() {
        return end;
    }

    public void setEnd(Double[] end) {
        this.end = end;
    }

    public String getVehicle_types() {
        return vehicle_types;
    }

    public void setVehicle_types(String vehicle_types) {
        this.vehicle_types = vehicle_types;
    }
}
