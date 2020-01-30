/*
    CSC 320
    Lab 3
    
    File:  Maze.java
*/

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;

public class Maze {

    private MazeSquare[][] mazeMatrix;
    private int numRows;
    private int numCols;

    public Maze(int r, int c, int sizeOfASquare, int windowWidth) {
        numRows = r;
        numCols = c;
        
        mazeMatrix = new MazeSquare[numRows][numCols];
        
        // for maze to be centered horizontally, left-most column
        //    should start at 1/2 of (windowWidth - widthOfMaze)
        
        int xAnchor = (int) (0.5*(windowWidth - numCols * sizeOfASquare));
        
        for (r = 0; r < numRows; r++) {
            for (c = 0; c < numCols; c++) {
                mazeMatrix[r][c] = new MazeSquare(c*sizeOfASquare + xAnchor, r*sizeOfASquare + 30, sizeOfASquare);
            }
        }
        
        //****
        //**** UNCOMMENT THE STATEMENT THAT FOLLOWS THIS INSTRUCTION
        //****

        generateMaze();

    } // end of constructor()

    public void draw(Graphics g) {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mazeMatrix[r][c].draw( g );
            }
        }
        
        g.setColor(Color.green);
        MazeSquare startSq = mazeMatrix[0][0];
        g.fillRect(startSq.getX()+3, startSq.getY()+3, 
                   startSq.getSize()-6, startSq.getSize()-6);

        g.setColor(Color.red);
        MazeSquare endSq = mazeMatrix[numRows-1][numCols-1];
        g.fillRect(endSq.getX()+3, endSq.getY()+3, 
                   endSq.getSize()-6, endSq.getSize()-6);
    } // end of draw()              

    public MazeSquare getSquare(int r, int c) {
        return mazeMatrix[r][c];
    } // end of getSquare()
    
    public int getRows() { return numRows; }
    public int getCols() { return numCols; }

    public void generateMaze() {
        int currRow = (int)(Math.random()*numRows);
        int currCol = (int)(Math.random()*numCols);
        
        //****
        //**** UNCOMMENT THE STATEMENT THAT FOLLOWS THIS INSTRUCTION
        //****
        
        StackOfObjs cellStack;
        
        //****
        //**** THE NUMBER OF CELLS IN THE MAZE IS numRows TIMES numCols
        //****
        //**** INSTANTIATE cellStack WITH A CAPACITY OF TWICE THE
        //**** NUMBER OF CELLS IN THE MAZE
        //****
        

        
        int totCells = numRows*numCols;
        int visitedCells = 1;
        cellStack = new StackOfObjs(2*totCells);
        
        while (visitedCells < totCells) {
            
            ArrayList neighbors = new ArrayList();
            if (currRow > 0 && !mazeMatrix[currRow][currCol].hasNBorder() ) {
                if (mazeMatrix[currRow-1][currCol].hasAllWallsIntact()) {
                    neighbors.add(new MazeGenObj(mazeMatrix[currRow-1][currCol], currRow-1, currCol) );
                }
            }
            if (currRow < numRows-1 && !mazeMatrix[currRow][currCol].hasSBorder() ) {
                if (mazeMatrix[currRow+1][currCol].hasAllWallsIntact()) {
                    neighbors.add(new MazeGenObj(mazeMatrix[currRow+1][currCol], currRow+1, currCol) );
                }

            }
            if (currCol > 0 && !mazeMatrix[currRow][currCol].hasWBorder() ) {
                if (mazeMatrix[currRow][currCol-1].hasAllWallsIntact()) {
                    neighbors.add(new MazeGenObj(mazeMatrix[currRow][currCol-1], currRow, currCol-1) );
                }
            }
            if (currCol < numCols-1 && !mazeMatrix[currRow][currCol].hasEBorder() ) {
                if (mazeMatrix[currRow][currCol+1].hasAllWallsIntact()) {
                    neighbors.add(new MazeGenObj(mazeMatrix[currRow][currCol+1], currRow, currCol+1) );
                }
            }
            
            if (neighbors.size() > 0) {
                MazeGenObj nextCell = (MazeGenObj) neighbors.get((int) (Math.random()*neighbors.size()));

                if (nextCell.getRow() == currRow - 1) { // north neighbor
                    mazeMatrix[currRow][currCol].removeNWall();
                    mazeMatrix[currRow-1][currCol].removeSWall();
                } // end if north neighbor
                else if (nextCell.getRow() == currRow + 1) { // south neighbor
                    mazeMatrix[currRow][currCol].removeSWall();
                    mazeMatrix[currRow+1][currCol].removeNWall();
                } // end if south neighbor
                else if (nextCell.getCol() == currCol - 1) { // west neighbor
                    mazeMatrix[currRow][currCol].removeWWall();
                    mazeMatrix[currRow][currCol-1].removeEWall();
                } // end if west neighbor
                else if (nextCell.getCol() == currCol + 1) { // east neighbor
                    mazeMatrix[currRow][currCol].removeEWall();
                    mazeMatrix[currRow][currCol+1].removeWWall();
                } // end if east neighbor

                //****
                //**** PUSH THE VARIABLE currRow ONTO THE cellStack
                //****
                cellStack.push(currRow);

                
                //****
                //**** PUSH THE VARIABLE currCol ONTO THE cellStack
                //****
                cellStack.push(currCol);

                
                
                if (nextCell.getRow() == currRow - 1)  // move north
                    currRow--;
                else if (nextCell.getRow() == currRow + 1)  // move south
                    currRow++;
                else if (nextCell.getCol() == currCol - 1) // move west
                    currCol--;
                else if (nextCell.getCol() == currCol + 1) // move east
                    currCol++;
                    
                visitedCells++;
            } // end if there are neighbors with intact walls
            else {
                //****
                //**** POP THE STACK INTO currCol, CASTING THE RETURN VALUE TO AN
                //**** Integer OBJECT
                //****
                currCol = (Integer) cellStack.pop();
                
                //****
                //**** POP THE STACK INTO currRow, CASTING THE RETURN VALUE TO AN
                //**** Integer OBJECT
                //****
                currRow = (Integer) cellStack.pop();
                
            } // end else
        } // end while
    } // end of generateMaze()

} // end of class Maze
