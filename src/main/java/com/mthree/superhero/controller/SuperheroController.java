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
    private int heroId = 0;

    public SuperheroController(SuperheroServiceLayer service, SuperheroView view) {
        this.service = service;
        this.view = view;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/addPower/{id}")
    private String addPower(@PathVariable int id, Model model) {
        model.addAttribute("heroVillain", service.getHeroVillain(id));
        heroId = id;
        //model.addAttribute("powers", service.getAllPowers());
        return "heroVillain/addPower";
    }
    
    @PostMapping("addPower/{id}")
    private String addPower(
            @ModelAttribute("heroVillain") HeroVillain heroVillain,
            @PathVariable int id,
            Model model) {
        service.addPower(id, heroId);
        model.addAttribute("power", getPower(id));
        model.addAttribute("heroVillain", getHeroVillain(heroId));
        return "heroVillain/addPowerSuccess";
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
    
    @GetMapping("/deleteHeroVillain")
    private String deleteHeroVillain(Model model) {
        model.addAttribute("heroesVillains", service.getAllHerosAndVillains());
        return "/heroVillain/deleteHeroVillain";
    }
    
    @PostMapping("/deleteHeroVillain/{id}")
    private String deleteHeroVillain(@PathVariable int id, Model model) {
        HeroVillain heroVillain = new HeroVillain();
        heroVillain.setIsHero(service.getHeroVillain(id).getIsHero());
        heroVillain.setName(service.getHeroVillain(id).getName());
        model.addAttribute("heroVillain", heroVillain);
        service.deleteHeroVillain(id);
        return "heroVillain/deleteHeroVillainSuccess";
    }
    
    @GetMapping("/editHeroVillainList")
    private String editHeroVillain(Model model) {
        model.addAttribute("heroesVillains", service.getAllHerosAndVillains());
        return "/heroVillain/editHeroVillainList";
    }
    
    @GetMapping("/editHeroVillain/{id}")
    private String editHeroVillain(@PathVariable int id, Model model) {
        model.addAttribute("heroVillain", service.getHeroVillain(id));
        model.addAttribute("editHeroVillain", new HeroVillain());
        return "/heroVillain/editHeroVillain";
    }
    
    @PostMapping("/editHeroVillain")
    private String editHeroVillain(@ModelAttribute("heroVillain") HeroVillain heroVillain) {
        service.editHeroVillain(heroVillain);
        return "/heroVillain/editHeroVillainSuccess";
    }
    
    @GetMapping("/createLocation")
    private String createLocation(Model model) {
        model.addAttribute("location", new Location());
        return "/location/createLocation";
    }
    
    @PostMapping("/createLocation")
    public String createLocation(@ModelAttribute("location") Location location) {
        service.createLocation(location.getLatitude(), location.getLongitude());
        return "/location/createLocationSuccess";
    }
    
        @GetMapping("/deleteLocation")
    private String deleteLocation(Model model) {
        model.addAttribute("locations", service.getAllLocations());
        return "/location/deleteLocation";
    }
    
    @PostMapping("/deleteLocation/{id}")
    private String deleteLocation(@PathVariable int id, Model model) {
        Location location = new Location();
        location.setLatitude(service.getLocation(id).getLatitude());
        location.setLongitude(service.getLocation(id).getLongitude());
        model.addAttribute("location", location);
        service.deleteLocation(id);
        return "location/deleteLocationSuccess";
    }
    
    @GetMapping("/editLocation")
    private String editLocation(Model model) {
        model.addAttribute("locations", service.getAllLocations());
        return "/location/editLocationList";
    }
    
    @GetMapping("/editLocation/{id}")
    private String editLocation(@PathVariable int id, Model model) {
        model.addAttribute("location", service.getLocation(id));
        model.addAttribute("editLocation", new HeroVillain());
        return "/location/editLocation";
    }
    
    @PostMapping("/editLocation")
    private String editLocation(@ModelAttribute("location") Location location) {
        service.editLocation(location);
        return "/location/editLocationSuccess";
    }
    
    @PostMapping("/createLoc")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Location> createLocation(Double latitude, Double longitude) {
        Location location = service.createLocation(latitude, longitude);
        
        if (location == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }
    
    @PostMapping("/createOrg")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Organization> createOrganization(boolean isForHero, String name) {
        Organization organization = service.createOrganization(isForHero, name);
        
        if (organization == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(organization);
    }
    
    @GetMapping("/createOrganization")
    private String createOrganization(Model model) {
        model.addAttribute("organization", new Organization());
        return "/organization/createOrganization";
    }
    
    @PostMapping("/createOrganization")
    public String createOrganization(@ModelAttribute("organization") Organization organization) {
        service.createOrganization(organization.getIsForHero(), organization.getName());
        return "/organization/createOrganizationSuccess";
    }
    
    @GetMapping("/deleteOrganization")
    private String deleteOrganization(Model model) {
        model.addAttribute("organization", service.getAllOrganizations());
        return "/organization/deleteOrganization";
    }
    
    @PostMapping("/deleteOrganization/{id}")
    private String deleteOrganization(@PathVariable int id, Model model) {
        Organization organization = new Organization();
        organization.setIsForHero(service.getOrganization(id).getIsForHero());
        organization.setName(service.getOrganization(id).getName());
        model.addAttribute("organization", organization);
        service.deleteOrganization(id);
        return "organization/deleteOrganizationSuccess";
    }
    
    @GetMapping("/editOrganization")
    private String editOrganization(Model model) {
        model.addAttribute("organizations", service.getAllOrganizations());
        return "/organization/editOrganizationList";
    }
    
    @GetMapping("/editOrganization/{id}")
    private String editOrganization(@PathVariable int id, Model model) {
        model.addAttribute("organization", service.getOrganization(id));
        model.addAttribute("editOrganization", new Organization());
        return "/organization/editOrganization";
    }
    
    @PostMapping("/editOrganization")
    private String editOrganization(@ModelAttribute("organization") Organization organization) {
        service.editOrganization(organization);
        return "/organization/editOrganizationSuccess";
    }
    
    @PostMapping("/createPow")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Power> createPower(String name, String description) {
        Power power = service.createPower(name, description);
        
        if (power == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(power);
    }
    
    @GetMapping("/createPower")
    private String createPower(Model model) {
        model.addAttribute("power", new Power());
        return "/power/createPower";
    }
    
    @PostMapping("/createPower")
    public String createPower(@ModelAttribute("power") Power power) {
        service.createPower(power.getName(), power.getDescription());
        return "/power/createPowerSuccess";
    }
    
    @GetMapping("/deletePower")
    private String deletePower(Model model) {
        model.addAttribute("power", service.getAllPowers());
        return "/power/deletePower";
    }
    
    @PostMapping("/deletePower/{id}")
    private String deletePower(@PathVariable int id, Model model) {
        Power power = new Power();
        power.setName(service.getPower(id).getName());
        power.setDescription(service.getPower(id).getDescription());
        model.addAttribute("power", power);
        service.deletePower(id);
        return "power/deletePowerSuccess";
    }
    
    @GetMapping("/editPower")
    private String editPower(Model model) {
        model.addAttribute("powers", service.getAllPowers());
        return "/power/editPowerList";
    }
    
    @GetMapping("/editPower/{id}")
    private String editPower(@PathVariable int id, Model model) {
        model.addAttribute("power", service.getPower(id));
        model.addAttribute("editPower", new Power());
        return "/power/editPower";
    }
    
    @PostMapping("/editPower")
    private String editPower(@ModelAttribute("power") Power power) {
        service.editPower(power);
        return "/power/editPowerSuccess";
    }
    
    @PostMapping("/createSight")
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
    
    @GetMapping("/createSighting")
    private String createSighting(Model model) {
        model.addAttribute("sighting", new Sighting());
        return "/sighting/createSighting";
    }
    
    @PostMapping("/createSighting")
    public String createSighting(@ModelAttribute("sighting") Sighting sighting) {
        service.createSighting(sighting.getLocation(), sighting.getHeroVillain());
        return "/sighting/createSightingSuccess";
    }
    
    @GetMapping("/deleteSighting")
    private String deleteSighting(Model model) {
        model.addAttribute("sighting", service.getAllSightings());
        return "/sighting/deleteSighting";
    }
    
    @PostMapping("/deleteSighting/{id}")
    private String deleteSighting(@PathVariable int id, Model model) {
        Sighting sighting = new Sighting();
        sighting.setHeroVillain(service.getSighting(id).getHeroVillain());
        sighting.setLocation(service.getSighting(id).getLocation());
        sighting.setDate(service.getSighting(id).getDate());
        model.addAttribute("sighting", sighting);
        service.deleteSighting(id);
        return "sighting/deleteSightingSuccess";
    }
    
    @GetMapping("/editSighting")
    private String editSighting(Model model) {
        model.addAttribute("powers", service.getAllPowers());
        return "/power/editPowerList";
    }
    
    @GetMapping("/editSighting/{id}")
    private String editSighting(@PathVariable int id, Model model) {
        model.addAttribute("power", service.getPower(id));
        model.addAttribute("editPower", new Power());
        return "/power/editPower";
    }
    
    @PostMapping("/editSighting")
    private String editSighting(@ModelAttribute("sighting") Sighting sighting) {
        service.editSighting(sighting);
        return "/sighting/editSightingSuccess";
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

