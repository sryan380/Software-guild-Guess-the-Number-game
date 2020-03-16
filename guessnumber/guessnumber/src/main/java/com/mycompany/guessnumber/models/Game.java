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
public class Game {
    private int gameID;
    private int targetNum;
    private boolean isComplete;
    
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
     * @return the targetNum
     */
    public int getTargetNum() {
        return targetNum;
    }

    /**
     * @param targetNum the targetNum to set
     */
    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }
    
    /**
     * @return the isComplete
     */
    public boolean getIsComplete() {
        return isComplete;
    }

    /**
     * @param isComplete the isComplete to set
     */
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
