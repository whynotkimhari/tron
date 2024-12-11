/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * The Direction
 * @author Nguyen Kim Hai, Bui
 */
public enum Direction {
    DOWN(0, 1), 
    LEFT(-1, 0), 
    UP(0, -1), 
    RIGHT(1, 0);
    
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * coordinates
     */
    public final int x, y;
}
