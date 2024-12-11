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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Level;
import model.Player;
import model.State;
import java.util.Map.Entry;
import static java.util.Map.entry;

/**
 * The Menu of the game
 * @author Nguyen Kim Hai, Bui
 */

public class Menu extends BaseWindow {
    private final ArrayList<Game> games = new ArrayList<>();
    private final JLabel errorLabel = new JLabel("");
    private final InputPanel[] ips = new InputPanel[] { 
        new InputPanel("Player 1", this),
        new InputPanel("Player 2", this)
    };
        
    /**
     * Create the menu window for the game with Welcome message, Input boxes and level Buttons
     */
    public Menu() {
        errorLabel.setForeground(Color.RED);
        getContentPane().setLayout(new GridLayout(5, 1));
        
        // Banner
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(
            new JLabel(
            """
                <html>
                    <div align='center'>
                        <h1>TRON</h1>
                        <p>Hi players! Welcome to Tron!</p>
                        <p>Please type your names, choose colors, and ready to play!</p>
                    </div>
                </html>
                """
            )
        );
        getContentPane().add(welcomePanel);
        
        // Input panels
        getContentPane().add(ips[0]);
        getContentPane().add(ips[1]);
        
        // Error panel
        JPanel errorNotifier = new JPanel();
        errorNotifier.add(errorLabel);
        getContentPane().add(errorNotifier);
        
        // Level panel
        JPanel lvPanel = new JPanel(new FlowLayout());
        
        for (Level lv: Level.values()) {
            JButton button = new JButton();
            button.setText(lv.name());
            button.setPreferredSize(new Dimension(150, 50));

            button.addActionListener(e -> handleLvChosen(lv));
            lvPanel.add(button);
        }
        getContentPane().add(lvPanel);
    }
    
    /**
     * Get the input information and create player if the condition is met
     * @param id player id, 1 or 2
     * @return pair of a boolean and a Player
     */
    private Entry<Boolean, Player> getInput(int id) {
        InputPanel input = (InputPanel) getContentPane().getComponent(id);
        
        try {
            return entry(true, 
                new Player(
                    input.getPlayerName(),
                    input.getColor(),
                    id,
                    input.getVehicleName()
                )
            );
        }
        
        catch (IllegalArgumentException e) {
            return entry(false, new Player());
        }
    }
    
    /**
     * Validate the players
     * @param p1 player 1
     * @param p2 player 2
     * @return boolean
     */
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
    
    /**
     * Start the game if everything is OK
     * @param lv game level
     */
    private void handleLvChosen(Level lv) {
        Entry<Boolean, Player> rs1 = getInput(1);
        Entry<Boolean, Player> rs2 = getInput(2);

        if (!rs1.getKey() || !rs2.getKey()) return;

        Player p1 = rs1.getValue();
        Player p2 = rs2.getValue();

        if (!validate(p1, p2)) return;
        start(p1, p2, lv);
    }
    
    /**
     * Start the game with players and level
     * @param p1 player 1
     * @param p2 player 2
     * @param lv level
     */
    private void start(Player p1, Player p2, Level lv) {
        db.registerPlayers(p1.name, p2.name);
        
        // Start the game
        State state = new State(p1, p2, lv);
        Board board = new Board(state);
        Game game = new Game(board, this);
        state.run();
    }
   
    /**
     * Terminate the program
     */
    @Override
    protected void doUponExit() { System.exit(0); }
    public ArrayList<Game> getGames() { return games; }
}
