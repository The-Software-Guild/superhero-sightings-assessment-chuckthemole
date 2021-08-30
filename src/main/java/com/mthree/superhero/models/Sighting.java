/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Chuck
 */
public class Sighting implements Comparable<Sighting> {
    private int id;
    private Location location;
    private HeroVillain heroVillain;
    private LocalDate date;
    private LocalTime time;
    
    public Sighting() {}
    
    public Sighting(Location location, HeroVillain heroVillain) {
        this.location = location;
        this.heroVillain = heroVillain;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public HeroVillain getHeroVillain() {
        return this.heroVillain;
    }
    
    public void setHeroVillain(HeroVillain heroVillain) {
        this.heroVillain = heroVillain;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.id == ((Sighting) o).id;
    }
    
    @Override
    public String toString() {
        return "Name: " + heroVillain.getName() 
                + "  Location: " + location.getLatitude() 
                + ", " + location.getLongitude();
    }

    @Override
    public int compareTo(Sighting o) {
        return this.date.compareTo(o.getDate());
    }
    
}
