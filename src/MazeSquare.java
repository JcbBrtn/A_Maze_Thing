/*
    CSC 320
    Lab 3
    
    File:  MazeSquare.java
*/

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;


public class MazeSquare {

    private byte bitCode;
    private boolean visited;
    private byte visitedDirection;
    private boolean backtrackedInto;
    
    public final byte WALL_N   =     1;
    public final byte WALL_E   =     2;
    public final byte WALL_S   =     4;
    public final byte WALL_W   =     8;
    public final byte BORDER_N =    16;
    public final byte BORDER_E =    32;
    public final byte BORDER_S =    64;
    public final byte BORDER_W =   (byte) 128;
    
    public final byte VISITED_FROM_NORTH = 1;
    public final byte VISITED_FROM_SOUTH = 2;
    public final byte VISITED_FROM_EAST = 3;
    public final byte VISITED_FROM_WEST = 4;
    
    private int xPos;
    private int yPos;
    private int size;
    
    public MazeSquare( int x, int y, int sz ) {
        xPos = x;
        yPos = y;
        size = sz;
        
        bitCode = WALL_N + WALL_E + WALL_S + WALL_W; // all walls up initially
        visited = backtrackedInto = false;
    } // end of constructor()
    
    public void setNBorder() { bitCode |= BORDER_N; }
    public void setEBorder() { bitCode |= BORDER_E; }
    public void setSBorder() { bitCode |= BORDER_S; }
    public void setWBorder() { bitCode |= BORDER_W; }

    public void removeNWall() { if ((bitCode & WALL_N) != 0) bitCode -= WALL_N; }
    public void removeEWall() { if ((bitCode & WALL_E) != 0) bitCode -= WALL_E; }
    public void removeSWall() { if ((bitCode & WALL_S) != 0) bitCode -= WALL_S; }
    public void removeWWall() { if ((bitCode & WALL_W) != 0) bitCode -= WALL_W; }
    
    public int getX() { return xPos; }
    public int getY() { return yPos; }
    public int getSize() { return size; }
    
    public boolean hasNWall() { return (bitCode & WALL_N) != 0; }
    public boolean hasEWall() { return (bitCode & WALL_E) != 0; }
    public boolean hasSWall() { return (bitCode & WALL_S) != 0; }
    public boolean hasWWall() { return (bitCode & WALL_W) != 0; }
    
    public boolean hasAllWallsIntact() { return hasNWall() && hasEWall() && hasSWall() && hasWWall(); }

    public boolean hasNBorder() { return (bitCode & BORDER_N) != 0; }
    public boolean hasEBorder() { return (bitCode & BORDER_E) != 0; }
    public boolean hasSBorder() { return (bitCode & BORDER_S) != 0; }
    public boolean hasWBorder() { return (bitCode & BORDER_W) != 0; }

    public void markAsVisited(byte dir) { visited = true; visitedDirection = dir; }
    public void markAsUnvisited() { visited = false; }
    public void markAsBacktrackedInto() { backtrackedInto = true; }
    public void unmarkBacktrackedInto() { backtrackedInto = false; }
    
    public boolean hasBeenVisited() { return visited; }
    public boolean hasBeenBacktrackedInto() { return backtrackedInto; }
    
    public void draw(Graphics g) {
        // fill in square in background color to begin
        g.setColor(Color.black);
        g.fillRect(xPos, yPos, size, size);  // fill middle of square
        
        // color if we've been here before
        if (backtrackedInto) {
            g.setColor(new Color(0xdf, 0x82, 0x2a));
            g.fillRect(xPos+7, yPos+7, size-14, size-14);
        }
        else if (visited) {
            g.setColor(Color.yellow);
            Polygon arrow = new Polygon();
            if (visitedDirection == VISITED_FROM_NORTH) {
                arrow.addPoint(xPos+5, yPos+5);
                arrow.addPoint(xPos+size-5, yPos+5);
                arrow.addPoint(xPos+size/2, yPos+size-5);
            }
            else if (visitedDirection == VISITED_FROM_SOUTH) {
                arrow.addPoint(xPos+5, yPos+size-5);
                arrow.addPoint(xPos+size-5, yPos+size-5);
                arrow.addPoint(xPos+size/2, yPos+5);
            }
            else if (visitedDirection == VISITED_FROM_WEST) {
                arrow.addPoint(xPos+5, yPos+5);
                arrow.addPoint(xPos+5, yPos+size-5);
                arrow.addPoint(xPos+size-5, yPos+size/2);
            }
            else if (visitedDirection == VISITED_FROM_EAST) {
                arrow.addPoint(xPos+size-5, yPos+5);
                arrow.addPoint(xPos+size-5, yPos+size-5);
                arrow.addPoint(xPos+5, yPos+size/2);
            }
            g.fillPolygon(arrow);
        }   
        
        // fill in all walls and borders in gray
        
        g.setColor(Color.gray);
        if (hasNBorder() || hasNWall())  g.drawLine(xPos, yPos, xPos+size, yPos);
        if (hasEBorder() || hasEWall())  g.drawLine(xPos+size, yPos, xPos+size, yPos+size);
        if (hasSBorder() || hasSWall())  g.drawLine(xPos, yPos+size, xPos+size, yPos+size);
        if (hasWBorder() || hasWWall())  g.drawLine(xPos, yPos, xPos, yPos+size);
    } // end of draw()
    
} // end of class MazeSquare
