/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

import view.BaseWindow;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class Player {
    private static final Direction p1d = Direction.RIGHT;
    private static final Position p1p = new Position(BaseWindow.W / 4, BaseWindow.H / 2);
    private static final Direction p2d = Direction.LEFT;
    private static final Position p2p = new Position(BaseWindow.W * 3 / 4, BaseWindow.H / 2);
    
    private final int id;
    private Position pos;
    private Direction dir;
    
    public Vehicle veh;
    public final String name;
    public final Color color;
    public final String vehicleName;
    
    public Player() {
        id = -1;
        name = "";
        color = Color.GRAY;
        vehicleName = "";
    }
    
    public Player(String name, Color color, String vehicleName, int id) {
        this.name = name;
        this.color = color;
        this.vehicleName = vehicleName;
        this.id = id;
        init();
    }
    
    private void rotateVehicle() {
        veh = new Vehicle(vehicleName, dir.name().toLowerCase());
    }
    
    public void init() {
        this.dir = (id == 1) ? p1d : p2d;
        this.pos = (id == 1) ? new Position(p1p) : new Position(p2p);
        rotateVehicle();
    }
    
    public void setDir(Direction d) {
        dir = d;
        rotateVehicle();
    }
    
    public void move(int speed) { pos.translate(dir, speed); }
    public int getX() { return pos.getX(); }
    public int getY() { return pos.getY(); }
}
