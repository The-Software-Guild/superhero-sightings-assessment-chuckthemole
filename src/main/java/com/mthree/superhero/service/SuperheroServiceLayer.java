/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.service;

import com.mthree.superhero.models.HeroVillain;
import com.mthree.superhero.models.Location;
import com.mthree.superhero.models.Organization;
import com.mthree.superhero.models.Power;
import com.mthree.superhero.models.Sighting;
import java.util.List;

/**
 *
 * @author Chuck
 */
public interface SuperheroServiceLayer {
    // Create 
    HeroVillain createHeroVillain(boolean isHero, String name);
    Location createLocation(double longitude, double latitude);
    Organization createOrganization(boolean isForHero, String name);
    Power createPower(String name, String description);
    Sighting createSighting(Location location, HeroVillain heroVillain);
    
    // Getters
    List<HeroVillain> getAllherosAndVillains();
}
