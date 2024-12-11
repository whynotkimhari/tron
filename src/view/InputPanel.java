/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * The Input Panel
 * @author Nguyen Kim Hai, Bui
 */
public class InputPanel extends JPanel {
    private final JTextField textField = new JTextField(20);
    private final ColorPicker cp;
    private final JComboBox<String> vehicleNames = new JComboBox<>(
        new String[] {"cycle", "skate", "horse", "motor"}
    );
    
    /**
     * Error label below the input panel
     */
    public final JLabel errorLabel = new JLabel("");
    
    public InputPanel(String name, JFrame owner) {
        super(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(10, 10, 10, 10);
        
        JLabel titleLabel = new JLabel(name + "'s name: ");
        errorLabel.setForeground(Color.RED);
        cp = new ColorPicker(owner);
        
        JLabel optionLabel = new JLabel("Choose a vehicle for %s!".formatted(name));
        vehicleNames.setBounds(50, 50, 150, 30);
        vehicleNames.setSelectedItem("cycle");
        
        gbc.insets = insets;
        
        gbc.gridx = 0;  gbc.gridy = 0;  
        add(titleLabel, gbc);
        
        gbc.gridx = 1;  gbc.gridy = 0;  
        add(textField, gbc);
        
        gbc.gridx = 1;  gbc.gridy = 1;  
        add(errorLabel, gbc);
        
        gbc.gridx = 2;  gbc.gridy = 0;  
        add(cp, gbc);
        
        gbc.gridx = 3;  gbc.gridy = 0;  
        add(cp.button, gbc);
        
        gbc.gridx = 0;  gbc.gridy = 2;  
        add(optionLabel, gbc);
        
        gbc.gridx = 1;  gbc.gridy = 2;  
        add(vehicleNames, gbc);
    }
    
    /**
     * Get the player name
     * @return string
     * @throws IllegalArgumentException if name is empty
     */
    public String getPlayerName() throws IllegalArgumentException {
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    
        String name = textField.getText().strip();
        
        if (name.equals("")) {
            textField.setBorder(redBorder);
            errorLabel.setText("The name can not be an empty string!");
            
            throw new IllegalArgumentException();
        }
        
        textField.setBorder(defaultBorder);
        errorLabel.setText("");
        
        return textField.getText().strip();
    }
    
    /**
     * Get chosen color
     * @return color
     */
    public Color getColor() { return cp.getColor(); }
    
    /**
     * Get chosen vehicle name
     * @return string
     */
    public String getVehicleName() { return (String) vehicleNames.getSelectedItem(); }
}
