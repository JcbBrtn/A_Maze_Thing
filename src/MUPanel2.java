/*
    CSC 320
    Lab 3
    
    File:  MUPanel.java
  
    Maze Generation and user movement
*/

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MUPanel2 extends JPanel implements KeyListener {

    private Maze theMaze;
    private RandomMazeSolver randomFirstSolver;
    private Thread randomThread;
    private HumanMazeSolver player;
    

    

    public MUPanel2() {
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));
        setName("CSC 320 Lab # 3 -- Fun with Random Maze Solvers!");
        setBackground(Color.BLACK);

        theMaze = new Maze(13, 18, 20, 400);
        
        player = new HumanMazeSolver(theMaze, true);
        
        setFocusable( true );
        addKeyListener( this );

        
    } // end of MUPanel constructor
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // This line must be first in this method!
                
        g.setColor(Color.white);

        theMaze.draw( g );
        
        player.draw(g);
        
        if (randomThread == null) {
            randomFirstSolver = new RandomMazeSolver(theMaze, getGraphics());
            randomThread = new Thread(randomFirstSolver);
            randomThread.start();
        }
        

    } // end of method paintComponent
    
    
    public void keyPressed(KeyEvent event){
        int code = event.getKeyCode();
        String keyText = event.getKeyText(code);

        //****
        //**** UNCOMMENT THE FOLLOWING 10 LINES!

        //change the location based on the key pressed
        
        if (keyText.equals("Up")){
			player.moveUp();
        } else if (keyText.equals("Down")){
			player.moveDown();
        } else if (keyText.equals("Left")){
			player.moveLeft();
        } else if (keyText.equals("Right")){
			player.moveRight();
        }
        
		
        //now redraw the MUPanel window
        repaint(); 
    } // end of keyPressed()
	
    public void keyReleased(KeyEvent event){
        //System.out.println(event);		
    } // end of keyReleased()
	
    public void keyTyped(KeyEvent event){
        //System.out.println(event);		
    } // end of keyTyped()
    
    /***********************************************
     * Do NOT change or delete anything below here!
     ***********************************************/
    public void frame()
    {
        for (Component c: getComponents())
            c.setSize(c.getPreferredSize());
        JFrame f = new JFrame(getName());
        f.setContentPane(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);    
    }

    public static void main(String args[]){new MUPanel().frame();}

} // end of class MUPanel