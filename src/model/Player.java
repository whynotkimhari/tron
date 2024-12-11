/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

import view.BaseWindow;

/**
 * The Player
 * @author Nguyen Kim Hai, Bui
 */
public class Player {
    private static final Direction p1d = Direction.RIGHT;
    private static final Position p1p = new Position(BaseWindow.W / 4, BaseWindow.H / 2);
    private static final Direction p2d = Direction.LEFT;
    private static final Position p2p = new Position(BaseWindow.W * 3 / 4, BaseWindow.H / 2);
    
    private Position pos;
    private Direction d;
    
    /**
     * Player's id: 1 or 2
     */
    public final int id;
    
    /**
     * Player's vehicle
     */
    public final Vehicle veh;
    
    /**
     * Player's name
     */
    public final String name;
    
    /**
     * Player's color
     */
    public final Color color;
    
    public Player() {
        id = -1;
        name = "";
        color = Color.GRAY;
        veh = new Vehicle();
    }
    
    public Player(String name, Color color, int id, String vehName) {
        this.name = name;
        this.color = color;
        this.id = id;
        d = (id == 1) ? p1d : p2d;
        pos = (id == 1) ? new Position(p1p) : new Position(p2p);
        veh = new Vehicle(vehName, d);
    }
    
    /**
     * Create init the position and direction for player
     */
    public void init() {
        d = (id == 1) ? p1d : p2d;
        pos = (id == 1) ? new Position(p1p) : new Position(p2p);
        veh.rotate(d);
    }
    
    /**
     * Set direction for player and rotate his vehicle
     * @param d direction
     */
    public void setD(Direction d) {
        this.d = d;
        veh.rotate(d);
    }
    
    /**
     * Move the player
     * @param speed move speed
     */
    public void move(int speed) { pos.translate(d, speed); }
    public int getX() { return pos.getX(); }
    public int getY() { return pos.getY(); }
}
