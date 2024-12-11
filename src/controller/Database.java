/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import model.Record;

/**
 * Singleton Database Controller
 * @author Nguyen Kim Hai, Bui
 */
public class Database {
    private static Database instance = null;
    private Connection conn = null;
    
    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            MysqlConnectionPoolDataSource pool= new MysqlConnectionPoolDataSource();
            pool.setServerName("localhost");
            pool.setPort(3306);
            pool.setDatabaseName("Tron");
            pool.setUser("root");
            pool.setPassword("wnkh");

            conn = pool.getPooledConnection().getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No connection Database!");
        }
    }
    
    /**
     * Method to get singleton instance
     * @return 
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        
        return instance;
    }
    
    /**
     * Get all records
     * @return 
     */
    public ArrayList<Record> getRecords() {
        try {
            ArrayList<Record> highScores = new ArrayList<>();
            
            // Execute the query for the first player
            try (Statement statement = conn.createStatement()) {
                // Check if the winner is in database
                ResultSet rs = statement.executeQuery(
                "SELECT * FROM player ORDER BY 2 DESC, 1 ASC LIMIT 10;"
                );  
                
                while (rs.next()) {
                    String name = rs.getString("name");
                    int score = rs.getInt("score");
                    
                    highScores.add(new Record(name, score));
                }
                
                return highScores;
            }
            
        } catch (SQLException z) {
            System.err.println("SQL error happened. Cannot add new players into table!");
            return null;
        }
    }
    
    /**
     * Register two players to database
     * @param name1 player 1's name
     * @param name2 player 2's name
     */
    public void registerPlayers(String name1, String name2) {
        try {
            String sql = "INSERT IGNORE INTO player (name, score) VALUES (?, 0), (?, 0);";

            // Execute the query for the first player
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Execute the query for the first player
                pstmt.setString(1, name1);
                pstmt.setString(2, name2);
                
                pstmt.executeUpdate();
            }
            
        } catch (SQLException z) {
            System.err.println("SQL error happened. Cannot add new players into table!");
        }
    }
    
    /**
     * Get score of player with given name
     * @param name player name
     * @return int
     */
    public int getScore(String name) {
        try {
            // Check if the winner is in database
            try (Statement statement = conn.createStatement()) {
                // Check if the winner is in database
                ResultSet rs = statement.executeQuery(
                "SELECT score FROM player WHERE name='%s';"
                    .formatted(name)
                );  rs.first();
                return rs.getInt("score");
            }
            
        } catch (SQLException z) {
            System.out.println(z);
            System.err.println("SQL error happened. Cannot get player score!");
            return 0;
        }
    }
    
    /**
     * Update score of player with given name
     * @param name player's name
     */
    public void updateScore(String name) {
        int currScore = getScore(name);
        
        try {
            // Check if the winner is in database=
            try (Statement statement = conn.createStatement()) {
                // Check if the winner is in database=
                statement.executeUpdate(
                "UPDATE player SET score=%d WHERE name='%s';"
                    .formatted(currScore + 1, name)
                );
            }
            
        } catch (SQLException z) {
            System.out.println(z);
            System.err.println("SQL error happened. Cannot update score!");
        }
    }
}
