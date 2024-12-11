/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The Vehicle
 * @author Nguyen Kim Hai, Bui
 */
public class Vehicle {
    /**
     * Vehicle image width
     */
    public static final int W = 10;
    
    /**
     * Vehicle image height
     */
    public static final int H = 10;
    
    private String name;
    private Image img;
    
    public Vehicle() { }
    
    public Vehicle(String name, Direction d) {
        this.name = name;
        this.img = new ImageIcon(
            getClass().getClassLoader().getResource(
                "assets/%s-%s.png".formatted(
                    name, 
                    d.name().toLowerCase()
                )
            )
        ).getImage();
    }
    
    /**
     * Rotate the image to a given direction
     * @param d direction
     */
    public void rotate(Direction d) {
        this.img = new ImageIcon(
            getClass().getClassLoader().getResource(
                "assets/%s-%s.png".formatted(
                    name, 
                    d.name().toLowerCase()
                )
            )
        ).getImage();
    }

    public Image getImg() { return img; }
}
