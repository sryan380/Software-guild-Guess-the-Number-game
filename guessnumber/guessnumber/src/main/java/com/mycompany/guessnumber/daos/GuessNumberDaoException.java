/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.daos;

/**
 *
 * @author Steve
 */
public class GuessNumberDaoException extends Exception{
    
    public GuessNumberDaoException(String message){
        super(message);
    }
    public GuessNumberDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
