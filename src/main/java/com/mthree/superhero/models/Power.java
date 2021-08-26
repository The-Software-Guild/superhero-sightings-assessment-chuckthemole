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
public class Power {
    private int id;
    private String name;
    private String description;
    
    public Power() {}
    
    public Power(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.id == ((Power) o).id;
    }
}
