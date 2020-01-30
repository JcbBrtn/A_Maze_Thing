/*
    CSC 320
    Lab 3
    
    File:  HumanMazeSolver.java
*/

import java.awt.*;

public class HumanMazeSolver extends MazeSolver{
    
    public HumanMazeSolver (Maze m, boolean shouldLeaveTrail) {
        super(m);
        leaveTrail = shouldLeaveTrail;
    } // end of constructor
    
    public void solveMaze() { }
    
    public void moveDown() {
        MazeSquare theSq = theMaze.getSquare(row, col);
        if (!theSq.hasSWall() && !theSq.hasSBorder()) {
            row++;
            if (leaveTrail)
                theSq.markAsVisited(theSq.VISITED_FROM_NORTH);
            if (leaveTrail && (theMaze.getSquare(row, col)).hasBeenVisited()) 
                (theMaze.getSquare(row, col)).markAsBacktrackedInto();
        }
    } // end of moveDown()
    
    
    public void moveUp() {
         //****
         //**** FILL THIS IN SO THAT IT WORKS SIMILARLY TO moveDown()
         //****
         MazeSquare theSq = theMaze.getSquare(row, col);
         if (!theSq.hasNWall() && !theSq.hasNBorder()) {
            row--;
            if (leaveTrail)
                theSq.markAsVisited(theSq.VISITED_FROM_SOUTH);
            if (leaveTrail && (theMaze.getSquare(row, col)).hasBeenVisited()) 
                (theMaze.getSquare(row, col)).markAsBacktrackedInto();
        }
        
        
    } // end of moveUp()
    
    
    public void moveRight() {
        MazeSquare theSq = theMaze.getSquare(row, col);
        if (!theSq.hasEWall() && !theSq.hasEBorder()) {
            col++;
            if (leaveTrail)
                theSq.markAsVisited(theSq.VISITED_FROM_WEST);
            if (leaveTrail && (theMaze.getSquare(row, col)).hasBeenVisited()) 
                (theMaze.getSquare(row, col)).markAsBacktrackedInto();
        }
    } // end of moveRight()
    
    
    public void moveLeft() {
         //****
         //**** FILL THIS IN SO THAT IT WORKS SIMILARLY TO moveRight()
         //****
         MazeSquare theSq = theMaze.getSquare(row, col);
        if (!theSq.hasWWall() && !theSq.hasWBorder()) {
            col--;
            if (leaveTrail)
                theSq.markAsVisited(theSq.VISITED_FROM_EAST);
            if (leaveTrail && (theMaze.getSquare(row, col)).hasBeenVisited()) 
                (theMaze.getSquare(row, col)).markAsBacktrackedInto();
        }

    
    } // end of moveLeft()
    
    
    public void draw( Graphics g ) {
        g.setColor(Color.cyan);
        MazeSquare theSq = theMaze.getSquare(row, col);
        g.fillRect(theSq.getX()+3, theSq.getY()+3,
                   theSq.getSize()-6, theSq.getSize()-6);
    } // end of draw()
    
}  // end of class HumanMazeSolver
