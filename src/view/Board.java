/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import model.Direction;
import model.State;
import model.Player;
import model.Vehicle;

/**
 *
 * @author Nguyen Kim Hai, Bui
 */
public class Board extends JPanel {
    public final State state;
    private final BufferedImage canvas = new BufferedImage(
        BaseWindow.W, 
        BaseWindow.H, 
        BufferedImage.TYPE_INT_ARGB
    );
    
    public Board(State state) {
        this.state = state;
        state.setBoard(this);
        setFocusable(true);
        requestFocusInWindow();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                super.keyPressed(ke);
                Direction d = null;
                int id = -1;
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_LEFT   -> { d = Direction.LEFT;    id = 2; }
                    case KeyEvent.VK_RIGHT  -> { d = Direction.RIGHT;   id = 2; }
                    case KeyEvent.VK_UP     -> { d = Direction.UP;      id = 2; }
                    case KeyEvent.VK_DOWN   -> { d = Direction.DOWN;    id = 2; }
                    
                    case KeyEvent.VK_A      -> { d = Direction.LEFT;    id = 1; }
                    case KeyEvent.VK_D      -> { d = Direction.RIGHT;   id = 1; }
                    case KeyEvent.VK_W      -> { d = Direction.UP;      id = 1; }
                    case KeyEvent.VK_S      -> { d = Direction.DOWN;    id = 1; }
                }
                
                if (id != -1)
                   state.setDirectionFor(id, d);
            }
        });
    }
    
    public void paintTrace(Graphics2D g2d, Player p) {
        g2d.setColor(p.color);
        g2d.fillRect(
            p.getX() - Vehicle.W / 2,
            p.getY() - Vehicle.H / 2,
            Vehicle.W, 
            Vehicle.H
        );
    }
    
    public void paintHead(Graphics2D g2d, Player p, int scaleFactor) {
        g2d.drawImage(
            p.veh.getImage(), 
            p.getX() - Vehicle.W * scaleFactor / 2,
            p.getY() - Vehicle.H * scaleFactor / 2,
            Vehicle.W * scaleFactor, 
            Vehicle.H * scaleFactor, 
            null
        );
    }
    
    public void clear() {
        // Clear the canvas by filling it with a transparent background
        Graphics2D canvasGraphics = canvas.createGraphics();
        canvasGraphics.setComposite(AlphaComposite.Clear); // Set the composite to clear
        canvasGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvasGraphics.dispose(); // Clean up the graphics object

        // Repaint the panel to reflect the cleared canvas
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Paint parent components

        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());

        // Draw the current canvas (persistent drawing)
        g.drawImage(canvas, 0, 0, null);
        Graphics2D canvasGraphics = canvas.createGraphics();

        // Draw traces
        paintTrace(canvasGraphics, state.p1);
        paintTrace(canvasGraphics, state.p2);

        // Draw the motorcycle image at the current position (without leaving a color)
        int scaleFactor = 6;
        paintHead(g2d, state.p1, scaleFactor);
        paintHead(g2d, state.p2, scaleFactor);

        // Clean up graphics object after all drawing
        g.dispose();
        g2d.dispose();
    }

    public BufferedImage getCanvas() { return canvas; }
}