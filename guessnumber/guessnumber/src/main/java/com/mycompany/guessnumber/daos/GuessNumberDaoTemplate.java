/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessnumber.daos;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Steve
 */
@Component
public class GuessNumberDaoTemplate implements GuessNumberDao{
    
    @Autowired
    JdbcTemplate template;

    @Override
    public Game createNewGame(Game newGame) {
        KeyHolder kh = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO games (targetNumber) VALUES(?)",
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, newGame.getTargetNum());

                    return ps;

                },
                kh);
        int generatedId = kh.getKey().intValue();
        newGame.setGameID(generatedId);
        return newGame;
        
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = template.query(
                "Select * From games"
                , new GameMapper());
        
        return games;
    }

    @Override
    public List<Game> getGameById(Integer gameID) {
        List<Game> game = template.query(
                "Select * from Games "
                + "Where gameid = ?"
                , new GameMapper(), gameID);
        return game;
    }

    @Override
    public List<Round> getAllRounds(Integer gameID) {
        List<Round> rounds = template.query(
                "Select * from rounds r " +
                "inner join games g on r.gameID = g.gameID " +
                "where r.gameID = ?"
                , new RoundMapper(), gameID);
        
        return rounds;
    }

    @Override
    public Round insertRound(Round newRound) {
        KeyHolder kh = new GeneratedKeyHolder();

        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO rounds (guess, gameID, exactmatches, partialmathces, timeGuess) VALUES(?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, newRound.getGuess());
                    ps.setInt(2, newRound.getGameID());
                    ps.setInt(3, newRound.getExactMatches());
                    ps.setInt(4, newRound.getPartialMathces());
                    Date toSet = Date.valueOf(newRound.getTimeGuess().toLocalDate());
                    ps.setDate(5, toSet);

                    return ps;

                },
                kh);
        int generatedId = kh.getKey().intValue();
        newRound.setRoundID(generatedId);
        return newRound;
    }

    @Override
    public void completeGame(Game aGame) throws GuessNumberDaoException{
        int rowsAffected = template.update("Update games SET isCompleted = 1 WHERE gameID = ?", aGame.getGameID());
        
        if (rowsAffected == 0) {
            throw new GuessNumberDaoException("Could not find id: " + aGame.getGameID());
        } else if (rowsAffected > 1) {
            throw new GuessNumberDaoException("The sky is falling.  All is lost.");
        }
    }
    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int i) throws SQLException {
            Game converted = new Game();
            
            converted.setGameID(row.getInt("gameID"));
            converted.setTargetNum(row.getInt("targetNumber"));
            converted.setIsComplete(row.getBoolean("isCompleted"));

            return converted;
        }
    }

    private static class RoundMapper implements RowMapper<Round> {
        
        @Override
        public Round mapRow(ResultSet row, int i) throws SQLException {
            Round converted = new Round();
            
            converted.setRoundID(row.getInt("roundId"));
            converted.setGuess(row.getInt("guess"));
            converted.setExactMatches(row.getInt("exactmatches"));
            converted.setPartialMathces(row.getInt("partialmathces"));
            converted.setGameID(row.getInt("gameID"));
            LocalDateTime toSet = row.getTimestamp("timeGuess").toLocalDateTime();
            converted.setTimeGuess(toSet);
            
            return converted;
        }
    }

}
