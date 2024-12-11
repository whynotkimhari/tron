/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * The Level
 * @author Nguyen Kim Hai, Bui
 */
public enum Level {
    EASY(2),
    STARTER(4),
    MEDIUM(6),
    ADVANCE(8),
    HARD(10);
    
    /**
     * Frame Per Centi Second
     */
    public final int FPCS;
    Level (int FPCS) { this.FPCS = FPCS; }
}
