/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chuck
 */
public class Organization {
    private int id;
    private boolean isForHero; // if not hero, is villain.
    private String name;
    private Map<String, HeroVillain> herosAndVillains;
    
    public Organization() {herosAndVillains = new HashMap<>();}
    
    public Organization(boolean isForHero, String name) {
        herosAndVillains = new HashMap<>();
        this.isForHero = isForHero;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getIsForHero() {
        return this.isForHero;
    }
    
    public void setIsForHero(boolean isForHero) {
        this.isForHero = isForHero;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addHeroVillain(HeroVillain heroVillain) {
        herosAndVillains.put(heroVillain.getName(), heroVillain);
    }
    
    public List<HeroVillain> getHerosAndVillains() {
        List<HeroVillain> heroVillainList = new ArrayList<>();
        heroVillainList.addAll(herosAndVillains.values());
        return heroVillainList;
    }
    
    public void setHerosAndVillains(List<HeroVillain> listHeroVillain) {
        herosAndVillains = new HashMap<>();
        listHeroVillain.forEach(heroVillain -> {
            herosAndVillains.put(heroVillain.getName(), heroVillain);
        });
    }
    
    public boolean isHeroOrVillainInOrganization(String name) {
        return herosAndVillains.containsKey(name);
    }
    
    public boolean isHeroOrVillainInOrganization(HeroVillain heroVillain) {
        if(herosAndVillains.containsKey(heroVillain.getName())) {
            return herosAndVillains.get(heroVillain.getName()).equals(heroVillain);
        }
        return false;
    }
    
    @Override
    public boolean equals(Object o) {
        return this.id == ((Organization) o).id;
    }
}
