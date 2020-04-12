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
import com.mycompany.guessnumber.service.InvalidGuessException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Round guessNum(@RequestBody Map<String, Integer> mapBody) throws GuessNumberDaoException, InvalidGuessException{
        int guess = mapBody.get("guess");
        int gameID = mapBody.get("gameID");
        return service.checkGuess(guess, gameID);
    }
    
    @GetMapping
    @RequestMapping("/game")
    public List<Game> getAllGames(){
        return service.getAllGames();
    }
    
    @GetMapping
    @RequestMapping("/game/{id}")
    public Game getGameById(@PathVariable("id") int gameID) throws GuessNumberDaoException{
        Game aGame = service.displayGameById(gameID);
        return aGame;
    }
    
    @GetMapping
    @RequestMapping("/rounds/{id}")
    public List<Round> getrounds(@PathVariable("id") int gameID) throws GuessNumberDaoException{
        return service.getAllRounds(gameID);
    }
}
