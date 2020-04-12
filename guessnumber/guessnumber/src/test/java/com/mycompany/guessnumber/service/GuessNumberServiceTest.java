/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.service;

import com.mycompany.guessnumber.daos.GuessNumberDaoException;
import com.mycompany.guessnumber.daos.GuessNumberInMemDao;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Steve
 */
public class GuessNumberServiceTest {
    
    
    GuessNumberInMemDao dao = new GuessNumberInMemDao();
    GuessNumberService toTest = new GuessNumberService(dao);
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateGameGoldenPath() {
        Game toCheck = new Game();
        toCheck = toTest.createGame(toCheck);
        
        assertEquals(3, toCheck.getGameID());
        assertEquals(0, toCheck.getTargetNum());
        assertFalse(toCheck.getIsComplete());
        
    }

    @Test
    public void testCheckGuessGoldenPath() {
        Round toCheck = new Round();
        try {
            toCheck = toTest.checkGuess(7260, 1);
        } catch (GuessNumberDaoException ex) {
            fail("threw a GuessNumberDaoException on testCheckGuessGoldenPath");
        } catch (InvalidGuessException ex) {
            fail("threw a InvalidGuessException on testCheckGuessGoldenPath");
        }
        
        assertEquals(1, toCheck.getGameID());
        assertEquals(4, toCheck.getExactMatches());
        assertEquals(0, toCheck.getPartialMathces());
        assertEquals(7260, toCheck.getGuess());
    }

    @Test
    public void testGetAllGamesGoldenPath() {
        List<Game> allGames = toTest.getAllGames();
        
        assertEquals(1, allGames.get(0).getGameID());
        assertEquals(0, allGames.get(0).getTargetNum());
        assertFalse(allGames.get(0).getIsComplete());
        
        assertEquals(2, allGames.get(1).getGameID());
        assertEquals(6510, allGames.get(1).getTargetNum());
        assertTrue(allGames.get(1).getIsComplete());
    }

    @Test
    public void testDisplayGameByIdGoldenPath() {
        Game aGame = new Game();
        try {
            aGame = toTest.displayGameById(2);
        } catch (GuessNumberDaoException ex) {
            fail("threw a GuessNumberDaoException on testGetGameByIdGoldenPath");
        }
        
        assertEquals(2, aGame.getGameID());
        assertEquals(6510, aGame.getTargetNum());
        assertTrue(aGame.getIsComplete());
    }

    @Test
    public void testGetAllRoundsGoldenPath()  {
        try {
            toTest.checkGuess(1234, 1);
            toTest.checkGuess(7041, 1);
        } catch (GuessNumberDaoException ex) {
            fail("Threw a GuessNumberDaoException on testGetAllRoundsGoldenPath");
        } catch (InvalidGuessException ex) {
            fail("Threw a InvalidGuessException on testGetAllRoundsGoldenPath");
        }
        
        List<Round> allRounds = new ArrayList();
        try {
            allRounds = toTest.getAllRounds(1);
        } catch (GuessNumberDaoException ex) {
            fail("Threw a GuessNumberDaoException on testGetAllRoundsGoldenPath");
        }
        
        assertEquals(1, allRounds.get(0).getGameID());
        assertEquals(1234, allRounds.get(0).getGuess());
        assertEquals(1, allRounds.get(0).getExactMatches());
        assertEquals(0, allRounds.get(0).getPartialMathces());
        assertEquals(LocalDateTime.now(), allRounds.get(0).getTimeGuess());
        
        assertEquals(1, allRounds.get(1).getGameID());
        assertEquals(7041, allRounds.get(1).getGuess());
        assertEquals(1, allRounds.get(1).getExactMatches());
        assertEquals(1, allRounds.get(1).getPartialMathces());
    }
    
    @Test
    public void testCheckGuessInvalidGuess() {
        Round toCheck = new Round();
        try {
            toCheck = toTest.checkGuess(7260, 1);
        } catch (GuessNumberDaoException ex) {
            fail("threw a GuessNumberDaoException on testCheckGuessGoldenPath");
        } catch (InvalidGuessException ex) {

        }
    }
    
    @Test
    public void testDisplayGameByIdInvaildGameId() {
        Game aGame = new Game();
        try {
            aGame = toTest.displayGameById(-1);
        } catch (GuessNumberDaoException ex) {

        }
    }
    
    @Test
    public void testGetAllRoundsInvalidGameId(){
        
        List<Round> allRounds = new ArrayList();
        try {
            allRounds = toTest.getAllRounds(-1);
        } catch (GuessNumberDaoException ex) {

        }   
    }
}
