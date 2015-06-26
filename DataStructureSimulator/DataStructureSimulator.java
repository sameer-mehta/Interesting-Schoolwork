
/*
 * @Purpose: Program to visually demonstrate basic Data Structures such as Linked Lists and Stacks
 * @Author: Sameer Mehta
 * @Date: April 7th,2015
 */
import javax.swing.*;

public class DataStructureSimulator
{
   final static int HEIGHT = 500;//Width of the frame
   final static int WIDTH = 700;//height of the frame

   public static void main(String[] args)
   {
      SimViewer frame =  new SimViewer(); //Makes the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(WIDTH, HEIGHT);
      frame.setVisible(true);//Set Visibility
   }
}