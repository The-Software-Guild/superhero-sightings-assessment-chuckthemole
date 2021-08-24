/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.models;

import java.time.LocalDate;

/**
 *
 * @author Chuck
 */
public class Sighting {
    private int id;
    private Location location;
    private HeroVillain heroVillain;
    private LocalDate date;
    
    public Sighting() {}
    
    public Sighting(Location location, HeroVillain heroVillain) {
        this.location = location;
        this.heroVillain = heroVillain;
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
    
}
