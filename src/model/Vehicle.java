/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class Vehicle extends ImageIcon {
    public static final int W = 10;
    public static final int H = 10;
    
    public Vehicle(String vehicleName, String dirText) {
        
        super(
            Vehicle.class
                   .getClassLoader()
                   .getResource("assets/%s-%s.png".formatted(
                       vehicleName,
                       dirText
                   ))
        );
    }
}
