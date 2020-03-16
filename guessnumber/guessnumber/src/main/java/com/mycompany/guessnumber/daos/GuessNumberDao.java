/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.daos;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.util.List;

/**
 *
 * @author Steve
 */
public interface GuessNumberDao {

    public Game createNewGame(Game newGame);

    public List<Game> getAllGames();

    public List<Game> getGameById(Integer gameID);

    public List<Round> getAllRounds(Integer gameID);

    public Round insertRound(Round newRound);

    public void completeGame(Game aGame) throws GuessNumberDaoException;
    
}
