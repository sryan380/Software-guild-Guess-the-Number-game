 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.service;

import com.mycompany.guessnumber.daos.GuessNumberDao;
import com.mycompany.guessnumber.daos.GuessNumberDaoException;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Steve
 */
@Service
public class GuessNumberService {

    
    GuessNumberDao Dao;
    
    @Autowired
    public GuessNumberService(GuessNumberDao dao){
        this.Dao = dao;
    }

    public Game createGame(Game newGame) {
        generateTargetNum(newGame);
        newGame = Dao.createNewGame(newGame);
        newGame.setTargetNum(0000);
        return newGame;
    }

    public Round checkGuess(Integer guess, Integer gameID) throws GuessNumberDaoException, InvalidGuessException {

        boolean isUnique = false;
        int tempGuess = guess;
        int first = tempGuess % 10;
        tempGuess /= 10;
        int second = tempGuess % 10;
        tempGuess /= 10;
        int third = tempGuess % 10;
        tempGuess /= 10;
        int fourth = tempGuess % 10;
        
        if (second != first) {
            if (third != second && third != first) {
                if (fourth != third && fourth != second && fourth != first) {
                    isUnique = true;
                }
            }
        }

        if (isUnique == false) {
            throw new InvalidGuessException("Guess has to be 4 unique digits");
        }

        Round newRound = new Round();
        Game aGame = getGameById(gameID);
        newRound.setGameID(aGame.getGameID());
        newRound.setGuess(guess);
        newRound.setTimeOfGuess(LocalDateTime.now());

        int[] arrayGuess = convertToArray(guess);
        int[] arrayTarget = convertToArray(aGame.getTargetNum());

        if (aGame.getTargetNum() == guess) {
            aGame.setIsComplete(true);
            newRound.setExactMatches(4);

            Dao.completeGame(aGame);
            return Dao.insertRound(newRound);
        }
        int exactMatches = 0;
        int partialMatches = 0;
        for (int i = 0; i < arrayTarget.length; i++) {
            for (int j = 0; j < arrayGuess.length; j++) {
                if (arrayTarget[i] == arrayGuess[j]) {
                    if (i == j) {
                        exactMatches++;
                    } else {
                        partialMatches++;
                    }
                }
            }
        }
        if (!aGame.getIsComplete()){
            aGame.setTargetNum(0000);
        }

        newRound.setExactMatches(exactMatches);
        newRound.setPratialMatches(partialMatches);

        return Dao.insertRound(newRound);
    }

    public List<Game> getAllGames() {
        return Dao.getAllGames();
    }

    public Game getGameById(Integer gameID) throws GuessNumberDaoException {
        List<Game> games = Dao.getGameById(gameID);
        if(games.isEmpty()){
            throw new GuessNumberDaoException("Not a Valid gameID");
        }
        return games.get(0);
    }

    public List<Round> getAllRounds(Integer gameID) throws GuessNumberDaoException {
        getGameById(gameID);
        return Dao.getAllRounds(gameID);
    }

    private int[] convertToArray(Integer toConvert) {
        String temp = Integer.toString(toConvert);
        int[] toReturn = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            toReturn[i] = temp.charAt(i) - '0';
        }
        return toReturn;
    }

    private Game generateTargetNum(Game newGame) {
        Random rng = new Random();
        int first = rng.nextInt(9 - 1 + 1) + 1;
        int second = 0;
        int third = 0;
        int fourth = 0;
        boolean isUnique = false;

        while (!isUnique) {
            second = rng.nextInt(9 - 0 + 1);
            third = rng.nextInt(9 - 0 + 1);
            fourth = rng.nextInt(9 - 0 + 1);
            if (second != first) {
                if (third != second && third != first) {
                    if (fourth != third && fourth != second && fourth != first) {
                        isUnique = true;
                    }
                }
            }
        }

        Integer toSet = Integer.valueOf(String.valueOf(first) + String.valueOf(second) + String.valueOf(third) + String.valueOf(fourth));

        newGame.setTargetNum(toSet);
        return newGame;
    }

}
