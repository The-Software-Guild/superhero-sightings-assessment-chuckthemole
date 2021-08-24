/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.models;

import java.util.List;

/**
 *
 * @author Chuck
 */
public class HeroVillain {
    private int id;
    private boolean isHero; // if not hero, is villain.
    private String name;
    private List<Power> powers;
    
    public HeroVillain() {}
    
    public HeroVillain(boolean isHero, String name, List<Power> powers) {
        this.isHero = isHero;
        this.name = name;
        this.powers = powers;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getIsHero() {
        return this.isHero;
    }
    
    public void setIsHero(boolean isHero) {
        this.isHero = isHero;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Power> getPowers() {
        return this.powers;
    }
    
    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.id == ((HeroVillain) o).id;
    }
}
