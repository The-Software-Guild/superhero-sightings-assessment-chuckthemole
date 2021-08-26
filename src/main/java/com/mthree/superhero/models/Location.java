/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.models;

/**
 *
 * @author Chuck
 */
public class Location {
    private int id;
    private double longitude;
    private double latitude;
    
    public Location() {}
    
    public Location(Double latitude, Double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.id == ((Location) o).id;
    }
}
