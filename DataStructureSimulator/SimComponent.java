
/*
 * @purpose:Class that handles the simulation and contains the vehicles
 * @Auhtor: Sameer Mehta
 */
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimComponent extends JComponent
{
   private Vehicle selected; //The currently selected vehicle
   private Vehicle temp;
   public Vehicle engine; // The train engine Vehicle

   private RailCar r1, r2, r3, r4, r5;// The 5 rail cars


   
   ArrayList<Vehicle> list;//ArrayList to hold vehicles

   private boolean vehicleSelected;


   /*
    * @purpose:Default constructor for the simulation component
    */
   public SimComponent()
   {
      vehicleSelected = false;
    
      engine = new TrainEngine(-200, -200); //Initialize the train engine
      list = new ArrayList<Vehicle>();//Initialize the arraylist
      list.add(engine); //Add the train engine to the array list

      //@purpose:Creates the vehicles
      class MyListener extends MouseAdapter
      {
         public void mousePressed(MouseEvent event)
         {  
            int currentclickx = event.getX();
            int currentclicky = event.getY();
            
            
            engine.setUnSelected();
            
            //Loop to check if any vehicles are'nt selected
            for(Vehicle v : list){
               v.setUnSelected();
            }
            selected = null;

            //Loop to check if any vehicles don't overlap
            for(Vehicle v : list){     
               if(v.contains(currentclickx, currentclicky)){
                  selected = v;
                  v.setSelected();
                  vehicleSelected = true;
                  v.setCursorDifference(currentclickx-v.getX(), currentclicky-v.getY());
                  break;
               }
            }

            //adds rail cars
            if(!vehicleSelected){
               if(list.size()<6){
               addCar(currentclickx-10,currentclicky-10);
               }
               selected=null;
            }

            getFirst();
            repaint();
         }

         //@purpose: Once the mouse button has been released checks if any vehicles have been selected and if they are being linked
         public void mouseReleased(MouseEvent event)
         {  
            if(selected==null) return;

            for(Vehicle v : list){
               if(selected==v)   
                  continue;
               if(selected==engine)   
                  continue;
               if(selected.intersects(v)){
                  v.getLast().next=selected;
                  selected=null;
                  repaint();
                  v.setNextPosition();    
                  repaint();
                  break;
               }
            }
            
            repaint();
            vehicleSelected = false;
         }
      }
      addMouseListener(new MyListener());

      //@purpose:Implemenents Dragginf of rail cars and train engine
      class MyMouseListener implements MouseMotionListener
      {
         public void mouseDragged(MouseEvent event)
         {
            repaint();
            if(selected != null){
               int a = event.getX();
               int b = event.getY();
               selected.setPosition(a,b);
               selected.setPosition(a-selected.getXCursorDifference(), b-selected.getYCursorDifference());
               repaint();
            }
            repaint();
         }
         public void mouseMoved(MouseEvent event)
         {
         }
      }
      addMouseMotionListener(new MyMouseListener());
   }

   //@purpose:Used to restart the simulation from scratch
   //resets all the vehicles and repaints the component
   public void startSim()
   {
      engine.setPosition(-200, -200);
      engine.removeNext();
      resetVehicle(r1);
      resetVehicle(r2);
      resetVehicle(r3);
      resetVehicle(r4);
      resetVehicle(r5);
     
      r1=null;
      r2=null;
      r3=null;
      r4=null;
      r5=null;
   
      repaint();
   }
   
   //@purpose:Used to restart the simulation from a point
   //resets all the vehicles and repaints the component
   public void startSim(int x, int y)
   {
     
      startSim();
      engine.setPosition(x, y);
      repaint();
   } 

   //@purpose: Resets all the vehicles
   public void resetVehicle(Vehicle v)
   {
      if(v==null)
         return;
      v.next=null;
      list.remove(v);
      v=null;
   }

   //@purpose: Used to repaint the screen
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

      g2.setColor(Color.BLACK);

      if(selected!=null)
         selected.draw(g2);
      for(Vehicle v : list){
         v.draw(g2);
      }
   }

 
   //@purpose: Adds each rail car to the component at the given xLeft and yTop
   public void addCar(int x, int y)
   {
      if(engine.getX()<0){
         startSim(x,y);
         repaint();
         return;
      }
      if(!list.contains(r1)){
         r1 = new RailCar(x,y);
         r1.setValue(1);
         list.add(r1);
         return;
      }
      if(!list.contains(r2)){
         r2 = new RailCar(x,y);
         r2.setValue(2);
         list.add(r2);
         return;
      }
      if(!list.contains(r3)){
         r3 = new RailCar(x,y);
         r3.setValue(3);
         list.add(r3);
         return;
      }
      if(!list.contains(r4)){
         r4 = new RailCar(x, y);
         r4.setValue(4);
         list.add(r4);
         return;
      }
      if(!list.contains(r5)){
         r5 = new RailCar(x,y);
         r5.setValue(5);
         list.add(r5);
         return;
      }
     
      return;
   }
   
   // @purpose: Adds the selected rail car to the front of the linked list and repaint
   public void addFirst()
   {
      if(selected==null||selected==engine)
         return;
      
      selected.getLast().next=engine.next;
      engine.next=selected;
      getFirst();
      engine.setNextPosition();
      repaint();
   }
   
// @purpose: Adds the selected rail car to the back of the linked list and repaint
   public void addLast()
   {
      if(selected==null||selected==engine)
         return;
      
      if(engine.next==null){
         engine.next=selected;
         repaint();
         return;
      }
      engine.next.getLast().next=selected;
      engine.setNextPosition();
      repaint();
   }
   
   // @purpose: Removes the rail car from front of the linked list
   public void removeFirst()
   {
      
      if(engine.next==null) return;
      Vehicle temp = engine.next;
      engine.next = engine.next.next;
      resetVehicle(temp);
      repaint();
   }

  //@purpose: Removes the rail car from the back of the linked list
   public void removeLast()
   {
     
      if(engine.next==null) return;

      Vehicle secondLast = null;
      Vehicle temp = engine.next;

      if(temp.next==null){
         engine.next=null;
         resetVehicle(temp);
         repaint();
         return;
      }

      while(temp.next!=null){
         if(temp.next.next==null)
            secondLast = temp;
         temp = temp.next;
      }
      resetVehicle(temp);
      secondLast.next = null;
      repaint();
   }

  //@purpose:Selectes the first item on the linked list
   public void getFirst()
   {
      if(vehicleSelected){
         for(int i = 0; i<6; i++){
            for(Vehicle v : list)
            {
               if(selected!=v){
                  if(v.next==selected){
                     selected=v;
                     v.setSelected();
                  }
               }
            }
         }
      }
      repaint();
     
   } 
}