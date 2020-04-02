/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.daos;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steve
 */
public class GuessNumberInMemDao implements GuessNumberDao{
    
    List<Game> allGames = new ArrayList();
    List<Round> allRounds = new ArrayList();
    
    public GuessNumberInMemDao() {
        Game game1 = new Game();
        
        game1.setGameID(1);
        game1.setTargetNum(7260);
        game1.setIsComplete(false);
        allGames.add(game1);
        
        Game game2 = new Game();
        
        game2.setGameID(2);
        game2.setTargetNum(6510);
        game2.setIsComplete(true);
        
        allGames.add(game2);
    }
    
    @Override
    public Game createNewGame(Game newGame) {
        newGame.setGameID(allGames.size() + 1);
        return newGame;
        
    }

    @Override
    public List<Game> getAllGames() {
        return allGames;
    }

    @Override
    public List<Game> getGameById(Integer gameID) {
        List<Game> game = new ArrayList();
        
        for (Game x : allGames){
            if (x.getGameID() == gameID){
                game.add(x);
            }
        }
        
        return game;
    }

    @Override
    public List<Round> getAllRounds(Integer gameID) {
        return allRounds;
    }

    @Override
    public Round insertRound(Round newRound) {
        allRounds.add(newRound);
        return newRound;
    }

    @Override
    public void completeGame(Game aGame) throws GuessNumberDaoException{
        
        aGame.setIsComplete(true);
        
    }
}
