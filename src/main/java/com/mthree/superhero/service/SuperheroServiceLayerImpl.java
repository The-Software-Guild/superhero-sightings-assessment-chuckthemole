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

    public SuperheroServiceLayerImpl(SuperheroDao dao) {
        heroVillainHash = new HashMap<>();
        this.dao = dao;
    }

    @Override
    public HeroVillain createHeroVillain(boolean isHero, String name) {
        if (heroVillainHash.isEmpty() || heroVillainHash == null) {
            for (HeroVillain superPerson : dao.getAllHerosAndVillains()) {
                heroVillainHash.put(superPerson.getName(), superPerson);
            }
        }
        
        if (!heroVillainHash.containsKey(name)) {
            return dao.createHeroVillain(new HeroVillain(isHero, name, null));
        } else {
            // hero exists
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public Location createLocation(double longitude, double latitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization createOrganization(boolean isForHero, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Power createPower(String name, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sighting createSighting(Location location, HeroVillain heroVillain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HeroVillain> getAllherosAndVillains() {
        return dao.getAllHerosAndVillains();
    }
    
}
