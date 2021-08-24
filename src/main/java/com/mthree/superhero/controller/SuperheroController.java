/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.superhero.controller;

import com.mthree.superhero.models.HeroVillain;
import com.mthree.superhero.service.SuperheroServiceLayer;
import com.mthree.superhero.ui.SuperheroView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chuck
 */

@RestController
@RequestMapping("/api/superhero")
public class SuperheroController {
    private SuperheroView view;
    private SuperheroServiceLayer service;


    public SuperheroController(SuperheroServiceLayer service, SuperheroView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        
        while (keepGoing) {
            int menuSelection = getMenuSelection();

            switch(menuSelection) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
                    break;
            }
                    
        }
        
        exitMessage();
        System.exit(0);
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    // @RequestBody Round round sending as JSON to hide url
    @PostMapping("/createHeroVillain")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<HeroVillain> createHeroVillain(boolean isHero, String name) {
        HeroVillain heroVillain = service.createHeroVillain(isHero, name);
        
        if (heroVillain == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(heroVillain);
    }
    
    @GetMapping("/herosAndVillains")
    private List<HeroVillain> getHerosAndVillains() {
        return service.getAllherosAndVillains();
    }
    
    private void playGame(int number) {
        int guess = view.getGuessForGame();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    } 
}

