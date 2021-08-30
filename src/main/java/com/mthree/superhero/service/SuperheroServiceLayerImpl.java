/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.service;

import com.mthree.superhero.data.SuperheroDao;
import com.mthree.superhero.models.HeroVillain;
import com.mthree.superhero.models.Location;
import com.mthree.superhero.models.Organization;
import com.mthree.superhero.models.Power;
import com.mthree.superhero.models.Sighting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Chuck
 */
@Configuration
public class SuperheroServiceLayerImpl implements SuperheroServiceLayer {
    SuperheroDao dao;
    Map<String, HeroVillain> heroVillainHash;
    Map<Integer, Location> locationHash;
    Map<String, Organization> organizationHash;
    Map<String, Power> powerHash;

    public SuperheroServiceLayerImpl(SuperheroDao dao) {
        this.dao = dao;
        heroVillainHash = new HashMap<>();
        locationHash = new HashMap<>();
        organizationHash = new HashMap<>();
        powerHash = new HashMap<>();
        loadHashMaps();
    }

    @Override
    public HeroVillain createHeroVillain(boolean isHero, String name) {
        if (heroVillainHash.containsKey(name)) {
            throw new UnsupportedOperationException("Hero exists!");
        }
        
        HeroVillain heroVillain = dao.createHeroVillain(new HeroVillain(isHero, name, null));
        heroVillainHash.put(heroVillain.getName(), heroVillain);
        
        return heroVillain;
    }

    @Override
    public Location createLocation(Double latitude, Double longitude) {        
        Location location = dao.createLocation(new Location(latitude, longitude));            
        locationHash.put(location.getId(), location);
        
        return location;
    }

    @Override
    public Organization createOrganization(boolean isForHero, String name) {
        if (organizationHash.containsKey(name)) {
            throw new UnsupportedOperationException("Organization exists!");
        }
        
        Organization organization = dao.createOrganization(new Organization(isForHero, name));
        organizationHash.put(organization.getName(), organization);
    
        return organization;
    }

    @Override
    public Power createPower(String name, String description) {
        if (powerHash.containsKey(name)) {
            throw new UnsupportedOperationException("Power exists!");
        }
        
        Power power = dao.createPower(new Power(name, description));
        powerHash.put(power.getName(), power);
        
        return power;
    }

    @Override
    public Sighting createSighting(Location location, HeroVillain heroVillain) {
        if (!heroVillainHash.containsKey(heroVillain.getName())) {
            throw new UnsupportedOperationException("No hero or villain!");
        }
        if (!locationHash.containsKey(location.getId())) {
            throw new UnsupportedOperationException("No location found!");
        }
        return dao.createSighting(new Sighting(location, heroVillain));
    }

    @Override
    public HeroVillain getHeroVillain(int id) {
        return dao.getHeroVillain(id);
    }

    @Override
    public Location getLocation(int id) {
        return dao.getLocation(id);
    }

    @Override
    public Organization getOrganization(int id) {
        return dao.getOrganization(id);
    }

    @Override
    public Power getPower(int id) {
        return dao.getPower(id);
    }

    @Override
    public Sighting getSighting(int id) {
        return dao.getSighting(id);
    }

    @Override
    public List<HeroVillain> getAllHerosAndVillains() {
        return dao.getAllHerosAndVillains();
    }
    
    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    @Override
    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }
    
    private void loadHashMaps() {
        loadHeroVillainHashMap();
        loadLocationHashMap();
        loadOrganizationHashMap();
        loadPowerHashMap();
    }
    
    private void loadHeroVillainHashMap() {
        dao.getAllHerosAndVillains().forEach(superPerson -> {
                heroVillainHash.put(superPerson.getName(), superPerson);
        });
    }
    
    private void loadLocationHashMap() {
        dao.getAllLocations().forEach(location -> {
                locationHash.put(location.getId(), location);
        });
    }
    
    private void loadOrganizationHashMap() {
        dao.getAllOrganizations().forEach(organization -> {
                organizationHash.put(organization.getName(), organization);
        });
    }
    
    private void loadPowerHashMap() {
        dao.getAllPowers().forEach(power -> {
                powerHash.put(power.getName(), power);
        });
    }

    @Override
    public boolean deleteHeroVillain(int id) {
        return dao.deleteHeroVillain(id);
    }

    @Override
    public HeroVillain editHeroVillain(HeroVillain heroVillain) {
        return dao.editHeroVillain(heroVillain, heroVillain.getId());
    }

    @Override
    public boolean deleteLocation(int id) {
        return dao.deleteLocation(id);
    }

    @Override
    public Location editLocation(Location location) {
        return dao.editLocation(location, location.getId());
    }

    @Override
    public boolean deleteOrganization(int id) {
        return dao.deleteOrganization(id);
    }

    @Override
    public boolean deletePower(int id) {
        return dao.deletePower(id);
    }

    @Override
    public boolean deleteSighting(int id) {
        return dao.deleteSighting(id);
    }

    @Override
    public Organization editOrganization(Organization organization) {
        return dao.editOrganization(organization, organization.getId());
    }

    @Override
    public Power editPower(Power power) {
        return dao.editPower(power, power.getId());
    }

    @Override
    public Sighting editSighting(Sighting sighting) {
        return dao.editSighting(sighting, sighting.getId());
    }

    @Override
    public boolean addPower(int powerId, int heroVillainId) {
        //heroVillainHash.get(heroVillainId).addPower(getPower(powerId));
        return dao.addPower(powerId, heroVillainId);
    }

    @Override
    public List<Power> getHeroVillainPowers(int id) {
        return dao.getHeroVillainPowers(id);
    }
}
