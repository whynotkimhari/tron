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
    private static MysqlConnectionPoolDataSource pooledConn = null;
    private Database() { }
    
    public static Connection getConnection() throws SQLException {
        if (pooledConn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                pooledConn = new MysqlConnectionPoolDataSource();
                pooledConn.setServerName("localhost");
                pooledConn.setPort(3306);
                pooledConn.setDatabaseName("Tron");
                pooledConn.setUser("root");
                pooledConn.setPassword("wnkh");
            } catch (ClassNotFoundException e) {
                System.out.println("No connection Database!");
            }
        }
        
        return pooledConn.getPooledConnection().getConnection();
    }
    
    public static ArrayList<Record> getLeaderboard() {
        try {
            Connection conn = Database.getConnection();
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
    
    public static void registerPlayers(String name1, String name2) {
        try {
            Connection conn = Database.getConnection();
            
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
    
    public static int getScore(String name) {
        try {
            Connection conn = Database.getConnection();

            int score;
            // Check if the winner is in database
            try (Statement statement = conn.createStatement()) {
                // Check if the winner is in database
                ResultSet rs = statement.executeQuery(
                "SELECT score FROM player WHERE name='%s';"
                    .formatted(name)
                );  rs.first();
                score = rs.getInt("score");
            }
            return score;
            
        } catch (SQLException z) {
            System.out.println(z);
            System.err.println("SQL error happened. Cannot get player score!");
            return 0;
        }
    }
    
    public static void updateScore(String name) {
        int currScore = getScore(name);
        
        try {
            Connection conn = Database.getConnection();

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
