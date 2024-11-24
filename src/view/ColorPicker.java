/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class ColorPicker extends JPanel {
    private Color color = Color.GRAY;
    public final JButton button = new JButton("Pick a Color");
    
    public ColorPicker(JFrame owner) {
        super();
        this.setPreferredSize(new Dimension(20, 20));
        setBackground(color);
        
        button.addActionListener((ActionEvent e) -> {
            Color selectedColor = JColorChooser.showDialog(
                owner, 
                "Choose your trace color!", 
                getBackground()
            );
            
            if (selectedColor != null) {
                setBackground(selectedColor);
                color = selectedColor;
            }
        });
    }
    
    public Color getColor() { return color; }
}
