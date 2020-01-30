/*
    CSC 320
    Lab 3
    
    File:  MazeSolver.java
*/

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;


public abstract class MazeSolver {

    protected Maze theMaze;
    protected int row;
    protected int col;
    protected boolean leaveTrail;

    public MazeSolver(Maze m) {
        theMaze = m;
        row = 0;     // starting maze square is always (0,0)
        col = 0;
        leaveTrail = false;
    } // end of constructor 

    public boolean hasReachedEndingSquare() {
        return row == theMaze.getRows()-1 && col == theMaze.getCols()-1;
    } // end of hasReachedEndingSquare()()

    public abstract void solveMaze();
    
    public abstract void draw( Graphics g );
    
} // end of class MazeSolver
