/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.controllers;

import com.mycompany.guessnumber.daos.GuessNumberDaoException;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import com.mycompany.guessnumber.service.GuessNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Steve
 */
@RestController
@RequestMapping("/api")
public class GuessNumberController {
    
    @Autowired
    GuessNumberService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/begin")
    public String startGame(){
        Game newGame = new Game(); 
        newGame = service.createGame(newGame);
        return "GameID: " + newGame.getGameID() + "\nCompleted: " + newGame.getIsComplete();
        
    }
    
    @PostMapping
    @RequestMapping("/guess")
    public Round guessNum(Integer guess, Integer gameID) throws GuessNumberDaoException{
        return service.checkGuess(guess, gameID);
    }
    
    @GetMapping
    @RequestMapping("/allgames")
    public List<Game> getAllGames(){
        return service.getAllGames();
    }
    
    @GetMapping
    @RequestMapping("/agame")
    public Game getGameById(Integer gameID){
        Game aGame = service.getGameById(gameID);
        return aGame;
    }
    
    @GetMapping
    @RequestMapping("/rounds")
    public List<Round> getrounds(Integer gameID){
        return service.getAllRounds(gameID);
    }
}
