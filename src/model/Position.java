/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * The Position
 * @author Nguyen Kim Hai, Bui
 */
public class Position {
    private int x, y;
    
    public Position(Position p) {
        x = p.x;
        y = p.y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Move position to new position
     * @param d direction
     * @param speed move speed 
     */
    public void translate(Direction d, int speed) {
        x += d.x * speed;
        y += d.y * speed;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}