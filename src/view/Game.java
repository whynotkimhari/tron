/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Database;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.State;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class Game extends BaseWindow {
    private final Menu menu;
    private final State state;
    
    public Game(Board board, Menu menu) {
        this.menu = menu;
        this.state = board.state;
        
        state.setGame(this);
        
        menuGameRestart.setEnabled(true);
        
        menuGameRestart.addActionListener((ActionEvent e) -> {
            state.restart();
            state.run();
        });
        
        timeLabel.setVisible(true);
        
        state.tc.setTarget(timeLabel);
        
        menu.getGames().add(this);
        
        add(board);
        setVisible(true);
    }
    
    private void endWith(String name) {
        int choice = JOptionPane.showConfirmDialog(
            this, 
            "Game is over. %s won! \nDo you want to restart?".formatted(name),
            "Game Over",
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            state.restart();
            state.run();
        }
    }
    
    public void run() { state.run(); }
    
    public void end(String playerName) {
        if (playerName.equals("Nobody")) {
            endWith("Nobody");
            return;
        }

        Database.updateScore(playerName);
        endWith(playerName);
    }
    
    @Override
    protected void doUponExit() {
        super.doUponExit();
        menu.getGames().remove(this);
    }
}
