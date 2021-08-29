/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.controller;

import com.mthree.superhero.models.HeroVillain;
import com.mthree.superhero.models.Location;
import com.mthree.superhero.models.Organization;
import com.mthree.superhero.models.Power;
import com.mthree.superhero.models.Sighting;
import com.mthree.superhero.service.SuperheroServiceLayer;
import com.mthree.superhero.ui.SuperheroView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Chuck
 */

@Controller
@RequestMapping("/superhero")
public class SuperheroController {
    private SuperheroView view;
    private SuperheroServiceLayer service;

    public SuperheroController(SuperheroServiceLayer service, SuperheroView view) {
        this.service = service;
        this.view = view;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // @RequestBody Round round sending as JSON to hide url
    @PostMapping("/addHeroVillain")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<HeroVillain> createHeroVillain(HeroVillain hv, HttpServletRequest request) {
        String isHeroString = request.getParameter("isHero");
        boolean isHeroBoolean = false;
        if (!isHeroString.equals("false")) {
            if (!isHeroString.equals("true")) {
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
            isHeroBoolean = true;
        } 
        
        HeroVillain heroVillain = service.createHeroVillain(isHeroBoolean, request.getParameter("name"));
        
        if (heroVillain == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(heroVillain);
    }
    
    @GetMapping("/createHeroVillain")
    private String createHeroVillain(Model model) {
        model.addAttribute("heroVillain", new HeroVillain());
        model.addAttribute("heroesVillains", service.getAllHerosAndVillains());
        return "/heroVillain/createHeroVillain";
    }
    
    @PostMapping("/createHeroVillain")
    public String createHeroVillainSubmitForm(@ModelAttribute("heroVillain") HeroVillain heroVillain) {
        // System.out.println(heroVillain.getName());
        // System.out.println(heroVillain.getIsHero());
        service.createHeroVillain(heroVillain.getIsHero(), heroVillain.getName());
        return "/heroVillain/createHeroVillainSuccess";
    }
    
    @PostMapping("/createLocation")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Location> createLocation(Double latitude, Double longitude) {
        Location location = service.createLocation(latitude, longitude);
        
        if (location == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }
    
    @PostMapping("/createOrganization")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Organization> createOrganization(boolean isForHero, String name) {
        Organization organization = service.createOrganization(isForHero, name);
        
        if (organization == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(organization);
    }
    
    @PostMapping("/createPower")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Power> createPower(String name, String description) {
        Power power = service.createPower(name, description);
        
        if (power == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(power);
    }
    
    @PostMapping("/createSighting")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Sighting> createSighting(int locationId, int superId) {
        Sighting sighting = service.createSighting(
                getLocation(locationId), 
                getHeroVillain(superId));
        
        if (sighting == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sighting);
    }
    
    @GetMapping("/herosAndVillains")
    private String getHerosAndVillains(Model model) {
        model.addAttribute("heroesAndVillains", service.getAllHerosAndVillains());
        return "heroVillain/heroesAndVillains";
    }  
    
    @GetMapping("/locations")
    private String getLocations(Model model) {
        model.addAttribute("locations", service.getAllLocations());
        return "location/locations";
    }
    
    @GetMapping("/organizations")
    private String getOrganizations(Model model) {
        model.addAttribute("organizations", service.getAllOrganizations());
        return "organization/organizations";
    }
    
    @GetMapping("/powers")
    private String getPowers(Model model) {
        model.addAttribute("powers", service.getAllPowers());
        return "power/powers";
    }
    
    @GetMapping("/sightings")
    private String getSightings(Model model) {
        model.addAttribute("sightings", service.getAllSightings());
        return "sighting/sightings";
    }
    
    @GetMapping("/heroVillain/{id}")
    private String getHeroVillain(@PathVariable int id, Model model) {
        model.addAttribute("heroVillain", service.getHeroVillain(id));
        return "heroVillain/showHeroVillain";
    }
    
    private HeroVillain getHeroVillain(int id) {
        return service.getHeroVillain(id);
    }
    
    @GetMapping("/location/{id}")
    private String getLocation(@PathVariable int id, Model model) {
        model.addAttribute("location", service.getLocation(id));
        return "location/showLocation";
    }
    
    private Location getLocation(int id) {
        return service.getLocation(id);
    }
    
    @GetMapping("/organization/{id}")
    private String getOrganization(@PathVariable int id, Model model) {
        model.addAttribute("organization", service.getOrganization(id));
        return "organization/showOrganization";
    }
    
    private Organization getOrganization(int id) {
        return service.getOrganization(id);
    }
    
    @GetMapping("/power/{id}")
    private String getPower(@PathVariable int id, Model model) {
        model.addAttribute("power", service.getPower(id));
        return "power/showPower";
    }
    
    private Power getPower(int id) {
        return service.getPower(id);
    }
    
    @GetMapping("/sighting/{id}")
    private String getSighting(@PathVariable int id, Model model) {
        model.addAttribute("sighting", service.getSighting(id));
        return "sighting/showSighting";
    }
    
    private Sighting getSighting(int id) {
        return service.getSighting(id);
    }
    
    private void playGame(int number) {
        int guess = view.getGuessForGame();
    }
}

