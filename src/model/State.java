/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.TimeCounter;
import view.Board;
import view.Game;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class State {
    private boolean isP1Lost = false;
    private boolean isP2Lost = false;
    
    private Game game;
    private Board board;
    private final Level lv;
    
    public final Player p1, p2;
    public final TimeCounter tc;
    
    public State(Player p1, Player p2, Level lv) {
        this.p1 = p1;
        this.p2 = p2;
        this.lv = lv;
        tc = new TimeCounter();
    }
    
    private boolean checkLost(Player p) {
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
    
    private void next() throws IllegalStateException {
        p1.move(lv.FPCS);
        p2.move(lv.FPCS);

        isP1Lost = checkLost(p1);
        isP2Lost = checkLost(p2);
        
        board.repaint();
        
        if (isP1Lost || isP2Lost)
            throw new IllegalStateException();
    }
    
    private Player getWinner() {
        // If no one won neither lost
        // May happen if thread is interrupted       
        if (!isP1Lost && !isP2Lost) return null;
        
        // If no one won, both lost
        if (isP1Lost && isP2Lost) return null;
        
        // One of them won
        return isP1Lost ? p2 : p1;
    }
    
    public void run() {
        board.repaint();
        tc.start();
        new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(10);
                    next();
                }
            } catch (InterruptedException | IllegalStateException e) {
                tc.stop();
                game.end(getWinner() == null ? "Nobody" : getWinner().name);
            }
            
        }).start();
    }
    
    public void restart() {
        p1.init();   isP1Lost = false;
        p2.init();   isP2Lost = false;
        board.clear();
        tc.restart();
    }
    
    public void setDirectionFor(int playerID, Direction d) {
        (playerID == 1 ? p1 : p2).setDir(d);
    }
    
    public void setGame(Game game) { this.game = game; }
    public void setBoard(Board board) { this.board = board; }
}
