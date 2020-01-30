/*
    CSC 320
    
    File:  StackOfObjs.java
    Note:  this file uses an array of Objects to store stack items
*/


public class StackOfObjs {

   private int maxSize;
   private Object[] stackArray;
   private int top;
//--------------------------------------------------------------
   public StackOfObjs(int s)       // constructor
      {
      maxSize = s;
      stackArray = new Object[maxSize];
      top = -1;
      }
//--------------------------------------------------------------
   public void push(Object j)  // put item on top of stack
      {
      top++;
      stackArray[top] = j;
      }
//--------------------------------------------------------------
   public Object pop()         // take item from top of stack
      {
      Object answer = stackArray[top];
      top--;
      return answer;
      }
//--------------------------------------------------------------
   public Object peek()        // peek at top of stack
      {
      return stackArray[top];
      }
//--------------------------------------------------------------
   public boolean isEmpty()    // true if stack is empty
      {
      return (top == -1);
      }
//--------------------------------------------------------------
   }  // end class StackOfObjs
