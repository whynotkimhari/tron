/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.TimeCounter;
import view.Board;
import view.Game;

/**
 * The game state
 * @author Nguyen Kim Hai, Bui
 */
public class State {
    private boolean isP1Lost = false;
    private boolean isP2Lost = false;
    
    private Game game;
    private Board board;
    private final Level lv;
    
    /**
     * Players
     */
    public final Player p1, p2;
    
    /**
     * Time counter
     */
    public final TimeCounter tc;
    
    public State(Player p1, Player p2, Level lv) {
        this.p1 = p1;
        this.p2 = p2;
        this.lv = lv;
        tc = new TimeCounter();
    }
    
    /**
     * Check if player with given id is lost
     * @param id player id: 1 or 2
     * @return boolean
     */
    private boolean isLost(int id) {
        Player p = id == p1.id ? p1 : p2;
        try {
            for (Direction d: Direction.values()) {
                int RGB = board.getCanvas().getRGB(
                    p.getX() + d.x * Vehicle.W / 2, 
                    p.getY() + d.y * Vehicle.H / 2
                );
                
                // Lost by collision
                if (RGB != p.color.getRGB() && RGB != 0)
                    return true;
            }
        } 
        
        // Lost by going out of board
        catch (ArrayIndexOutOfBoundsException e) { return true; }
        return false;
    }
    
    /**
     * Move to next state
     * @throws IllegalStateException 
     */
    private void next() throws IllegalStateException {
        p1.move(lv.FPCS);
        p2.move(lv.FPCS);

        isP1Lost = isLost(p1.id);
        isP2Lost = isLost(p2.id);
        
        board.repaint();
        
        if (isP1Lost || isP2Lost)
            throw new IllegalStateException();
    }
    
    /**
     * Start the state
     */
    public void run() {
        board.repaint();
        tc.start();
        new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(10);
                    game.timeLabel.setText(
                        "Playing time: %.2fs".formatted(
                           tc.getCounter()
                        )
                    );
                    next();
                }
            } catch (InterruptedException | IllegalStateException e) {
                tc.stop();
                
                // Only one of them won
                if (isP1Lost ^ isP2Lost) {
                    game.end((isP1Lost ? p2 : p1).name);
                }
                
                else {
                    game.end("Nobody");
                }
            }
            
        }).start();
    }
    
    /**
     * restart the state
     */
    public void restart() {
        p1.init();   isP1Lost = false;
        p2.init();   isP2Lost = false;
        board.clear();
        tc.restart();
    }
    
    /**
     * Set the direction for player with given id and given direction
     * @param id player id
     * @param d direction
     */
    public void setDirectionFor(int id, Direction d) { (id == 1 ? p1 : p2).setD(d); }
    
    public void setGame(Game game) { this.game = game; }
    public void setBoard(Board board) { this.board = board; }
}
