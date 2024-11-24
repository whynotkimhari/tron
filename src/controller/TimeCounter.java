/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class TimeCounter {
    private int counter = 0;
    private JLabel target;
    private final Timer timer;
    
    public TimeCounter() {
        timer = new Timer(1000, e -> count());
    }
    
    private void count() {
        counter++;
        update();
    }
    
    private void update() {
        target.setText("Playing time: %ds".formatted(counter));
    }
    
    public void restart() {
        counter = 0;
        update();
    }
    
    public void start() { timer.start(); }
    public void stop() { timer.stop(); }
    public void setTarget(JLabel target) { this.target = target; }
}
