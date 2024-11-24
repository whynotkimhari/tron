/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Database;
import model.Level;
import model.Player;
import model.State;

/**
 * The Menu of the game
 * @author Nguyen Kim Hai, Bui
 */

public class Menu extends BaseWindow {
    private final ArrayList<Game> games = new ArrayList<>();
    private final JLabel errorLabel = new JLabel("");
    private final InputPanel[] inputs = new InputPanel[] { 
        new InputPanel("Player 1", this),
        new InputPanel("Player 2", this)
    };
        
    /**
     * Create the menu window for the game with Welcome message, Input boxes and level Buttons
     */
    public Menu() {
        errorLabel.setForeground(Color.RED);
        getContentPane().setLayout(new GridLayout(5, 1));
        
        getContentPane().add(createBanner());
        getContentPane().add(inputs[0]);
        getContentPane().add(inputs[1]);
        
        JPanel errorNotifier = new JPanel();
        errorNotifier.add(errorLabel);
        getContentPane().add(errorNotifier);
        getContentPane().add(createLevelPanel());
    }
    
    private JPanel createBanner() {
        StringBuilder sb = new StringBuilder();
        sb
        .append("<html>")
            .append("<div align='center'>")
                .append("<h1>TRON</h1>")
                .append("<p>Hi players! Welcome to Tron!</p>")
                .append("<p>Please type your names, choose colors, and ready to play!</p>")
            .append("</div>")
        .append("</html>");
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel(sb.toString()));
        
        return welcomePanel;
    }
    
    private Map.Entry<Boolean, Player> getInput(int id) {
        InputPanel input = (InputPanel) getContentPane().getComponent(id);
        
        try {
            return Map.entry(true, new Player(
                input.getPlayerName(),
                input.getColor(),
                input.getVehicleName(),
                id
            ));
        }
        
        catch (IllegalArgumentException e) {
            return Map.entry(false, new Player());
        }
    }
    
    private boolean validate(Player p1, Player p2) {
        if (p1.name.equals(p2.name)) {
            errorLabel.setText("Names must be unique!");
            return false;
        }

        if (p1.color.getRGB() == p2.color.getRGB()) {
            errorLabel.setText("Trace colors must be unique!");
            return false;
        }

        errorLabel.setText("");
        return true;
    }
    
    private void handleLevelChosen(Level gl) {
        Map.Entry<Boolean, Player> rs1 = getInput(1);
        Map.Entry<Boolean, Player> rs2 = getInput(2);

        if (!rs1.getKey() || !rs2.getKey()) return;

        Player p1 = rs1.getValue();
        Player p2 = rs2.getValue();

        if (!validate(p1, p2)) return;
        start(p1, p2, gl);
    }
    
    private void start(Player p1, Player p2, Level gl) {
        Database.registerPlayers(p1.name, p2.name);
        
        // Start the game
        State game = new State(p1, p2, gl);
        Board board = new Board(game);
        Game gameWindow = new Game(board, this);
        gameWindow.run();
    }
    
    private JButton createLevelButton(Level gl) {
        JButton button = new JButton();
        button.setText(gl.name());
        button.setPreferredSize(new Dimension(150, 50));
        
        button.addActionListener(e -> handleLevelChosen(gl));
        return button;
    }
    
    /**
     * Create the panel contains level buttons
     * @return JPanel
     */
    private JPanel createLevelPanel() {
        JPanel gameLevelPanel = new JPanel(new FlowLayout());
        
        for (Level gl: Level.values()) {
            gameLevelPanel.add(createLevelButton(gl));
        }
        
        return gameLevelPanel;
    }
    
    /**
     * Terminate the program
     */
    @Override
    protected void doUponExit() { System.exit(0); }
    public ArrayList<Game> getGames() { return games; }
}
