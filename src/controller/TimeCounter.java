/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.Timer;

/**
 * The time counter
 * @author Nguyen Kim Hai, Bui
 */
public class TimeCounter extends Timer {
    private float counter = 0;
    
    public TimeCounter() {
        super(10, null);
        addActionListener(e -> {
            counter += 0.01;
        });
    }
    
    /**
     * restart the counter to zero
     */
    public void restart() { counter = 0; }
    public float getCounter() { return counter; }
}
