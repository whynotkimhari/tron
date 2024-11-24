/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Database;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * This is the template window class
 * @author Nguyen Kim Hai, Bui
 */
public abstract class BaseWindow extends JFrame {
    public static final int W = 1000;
    public static final int H = 800;
    
    protected final JMenuItem menuLeaderBoard = new JMenuItem("Leaderboard");
    protected final JMenuItem menuGameRestart = new JMenuItem("Restart");
    protected final JLabel timeLabel = new JLabel("Playing time: 0s");
    /**
     * The constructor initial a centered square window whose side length is half of the screen width size
     */
    protected BaseWindow() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenuItem menuGameExit = new JMenuItem("Exit");
        
        timeLabel.setVisible(false);
        menuGameRestart.setEnabled(false);
        
        menuLeaderBoard.addActionListener((ActionEvent e) -> {
            new Leaderboard(
                Database.getLeaderboard(), 
                BaseWindow.this
            );
        });
        menuGameExit.addActionListener((ActionEvent e) -> System.exit(0));
        
        menuGame.add(menuLeaderBoard);
        menuGame.addSeparator();
        menuGame.add(menuGameRestart);
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        menuBar.add(timeLabel);
        setJMenuBar(menuBar);
        
        setTitle("Tron");
        setSize(W, H);

        // center the jframe on screen
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
    }
    
    /**
     * The Window will ask for the confirmation when user want to close it
     */
    private void showExitConfirmation() {
        int userOption = JOptionPane.showConfirmDialog(
            this, 
            "Do you really want to quit?",
            "Really?", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (userOption == JOptionPane.YES_OPTION)
            doUponExit();
        
    }
    
    /**
     * Close the window
     */
    protected void doUponExit() { dispose(); }
}
