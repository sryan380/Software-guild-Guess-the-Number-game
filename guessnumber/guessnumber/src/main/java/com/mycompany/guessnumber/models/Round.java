/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Steve
 */
public class Round {
    private int roundID;
    private int gameID;
    private int guess;
    private int exactMatches;
    private int partialMathces;
    private LocalDateTime timeGuess;

    /**
     * @return the roundID
     */
    public int getRoundID() {
        return roundID;
    }

    /**
     * @param roundID the roundID to set
     */
    public void setRoundID(int roundID) {
        this.roundID = roundID;
    }

    /**
     * @return the gameID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * @param gameID the gameID to set
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * @return the guess
     */
    public int getGuess() {
        return guess;
    }

    /**
     * @param guess the guess to set
     */
    public void setGuess(int guess) {
        this.guess = guess;
    }

    /**
     * @return the exactMatches
     */
    public int getExactMatches() {
        return exactMatches;
    }

    /**
     * @param exactMatches the exactMatches to set
     */
    public void setExactMatches(int exactMatches) {
        this.exactMatches = exactMatches;
    }

    /**
     * @return the partialMathces
     */
    public int getPartialMathces() {
        return partialMathces;
    }

    /**
     * @param partialMathces the pratialMatches to set
     */
    public void setPartialMathces(int partialMathces) {
        this.partialMathces = partialMathces;
    }

    /**
     * @return the timeGuess
     */
    public LocalDateTime getTimeGuess() {
        return timeGuess;
    }

    /**
     * @param timeGuess the timeOfGuess to set
     */
    public void setTimeGuess(LocalDateTime timeGuess) {
        this.timeGuess = timeGuess;
    }


}
