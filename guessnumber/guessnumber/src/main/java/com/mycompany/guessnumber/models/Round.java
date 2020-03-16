/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.models;

/**
 *
 * @author Steve
 */
public class Round {
    private int roundID;
    private int gameID;
    private int guess;
    private int exactMatches;
    private int pratialMatches;

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
     * @return the pratialMatches
     */
    public int getPratialMatches() {
        return pratialMatches;
    }

    /**
     * @param pratialMatches the pratialMatches to set
     */
    public void setPratialMatches(int pratialMatches) {
        this.pratialMatches = pratialMatches;
    }


}
