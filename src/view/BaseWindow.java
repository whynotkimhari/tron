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
    protected final Database db = Database.getDatabase();
    
    /**
     * Window Width
     */
    public static final int W = 1000;
    
    /**
     * Window Height
     */
    public static final int H = 800;
    
    protected final JMenuItem menuLeaderboard = new JMenuItem("Leaderboard");
    protected final JMenuItem menuGameRestart = new JMenuItem("Restart");
    
    /**
     * Time label
     */
    public final JLabel timeLabel = new JLabel("Playing time: 0s");
    
    protected BaseWindow() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenuItem menuGameExit = new JMenuItem("Exit");
        
        timeLabel.setVisible(false);
        menuGameRestart.setEnabled(false);
        
        menuLeaderboard.addActionListener((ActionEvent e) -> {
            new Leaderboard(
                db.getRecords(), 
                BaseWindow.this
            );
        });
        menuGameExit.addActionListener((ActionEvent e) -> System.exit(0));
        
        menuGame.add(menuLeaderboard);
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
                int userOption = JOptionPane.showConfirmDialog(
                    BaseWindow.this, 
                    "Do you really want to quit?",
                    "Really?", 
                    JOptionPane.YES_NO_OPTION
                );

                if (userOption == JOptionPane.YES_OPTION)
                    doUponExit();
            }
        });
    }
    
    /**
     * Close the window
     */
    protected void doUponExit() { dispose(); }
}
