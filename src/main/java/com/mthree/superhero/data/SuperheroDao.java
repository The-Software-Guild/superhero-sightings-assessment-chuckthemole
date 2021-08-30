/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.data;

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
public interface SuperheroDao {
    // Create 
    HeroVillain createHeroVillain(HeroVillain heroVillain);
    Location createLocation(Location location);
    Organization createOrganization(Organization organization);
    Power createPower(Power power);
    Sighting createSighting(Sighting sighting);
    
    // Edit
    HeroVillain editHeroVillain(HeroVillain heroVillain, int id);
    Location editLocation(Location location, int id);
    Organization editOrganization(Organization organization, int id);
    Power editPower(Power power, int id);
    Sighting editSighting(Sighting sighting, int id);    

    // Delete
    boolean deleteHeroVillain(int id);
    boolean deleteLocation(int id);
    Organization deleteOrganization(int id);
    Power deletePower(int id);
    Sighting deleteSighting(int id); 
    
    // Getters
    HeroVillain getHeroVillain(int id);
    Location getLocation(int id);
    Organization getOrganization(int id);
    Power getPower(int id);
    Sighting getSighting(int id); 
    List<HeroVillain> getAllHerosAndVillains();
    List<Location> getAllLocations();
    List<Organization> getAllOrganizations();
    List<Power> getAllPowers();
    List<Sighting> getAllSightings();
}
