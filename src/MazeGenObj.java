/*
    CSC 320
    Lab 3
    
    File:  MazeGenObj.java
*/

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;


public class MazeGenObj {
    private MazeSquare msq;
    private int row;
    private int col;
    
    public MazeGenObj( MazeSquare m, int r, int c ) {
        msq = m;
        row = r;
        col = c;
    } // end of MazeGenObj
    
    public int getRow() { return row; }
    public int getCol() { return col; }
    
    public MazeSquare getSquare() { return msq; }
    
} // end of class MazeGenObj
