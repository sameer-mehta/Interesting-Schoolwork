

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

//@Purpose: Creates the jframe which holds the simulation sc and the menu 
public class SimViewer extends JFrame
{
  
   private boolean programStarted; //Boolean variable used to determine if program has been started or not
   private SimComponent sc; //Simulation Component which holds the vehicles
 
   private String facename; //Used to store sub menu item name

   public SimViewer()
   {
      programStarted = false;
      sc = new SimComponent(); //initialize the simulation component
      this.add(sc); //add to jframe
  
      JMenuBar menuBar = new JMenuBar(); //create the menu bar
      setJMenuBar(menuBar); 
      menuBar.add(createFileMenu()); //add File menu
      menuBar.add(createStackMenu()); //add Stack menu
      menuBar.add(createListMenu()); //add Linked list menu

   }

   //@purpose: Starts a new Simulation
   public void startSimulator()
   {
      programStarted = true;
     sc.startSim();
   }

   /*
    * @purpose: Creates the File menu with its menu items : New and Exit
    * @return File Menu; the JMenu for the File Options
    */
   
   public JMenu createFileMenu()
   {
      JMenu menu = new JMenu("File");
      menu.add(createNewMenuItem("New"));//New menu item;allows user to start a new simulation
      menu.add(createNewExitItem("Exit"));//Exit menu item; allows user to exit the program

      return menu;
   }
   
   /*
    * @purpose: Creates the Stack menu with its menu items : Pop and Push
    * @return Stack Menu; the JMenu for the Stack Options
    */
   public JMenu createStackMenu()
   {
      JMenu menu = new JMenu("Stack");
      menu.add(createNewStackItem("Push")); //Pop menu item
     menu.add(createNewStackItem("Pop")); //Push Menu item

      return menu;
   }
   
   /*
    * @purpose: Creates the List menu with its menu items 
    * @return List Menu; the JMenu for the Linked List Options
    */
   public JMenu createListMenu()
   {
      JMenu menu = new JMenu("List");
      menu.add(createListMenuItem("Add First"));//Add first menu item; Adds selected rail car to the front of the linked list
      menu.add(createListMenuItem("Add Last"));//Add last menu item; Adds selected rail car to the back of the linked list
      menu.add(createListMenuItem("Remove First"));//Remove first menu item; Removes the first rail car from the linked list
      menu.add(createListMenuItem("Remove Last"));//Remove first menu item; Removes the last rail car from the linked list

      return menu;
   }
   
   /*@purpose:Creates the List menu 
    * @return List Menu; the JMenu for the list items
    */
   public JMenuItem createListMenuItem(final String name)
   {
	 //@purpose: Adds Linked List options Menu items to List menu and handles the click event for each item with the methods associated with them
      class FaceItemListener implements ActionListener 
      {
    	 // @purpose: Adds Linked List options Menu items to List menu and handles the click event for each item with the methods associated with them
    	public void actionPerformed(ActionEvent event)
         {
            facename = name;
            if(facename == "Add First"){
               sc.addFirst();
            }
            if(facename == "Add Last"){
               sc.addLast();
            }
            if(facename == "Remove First"){
               sc.removeFirst();
            }
            if(facename == "Remove Last"){
               sc.removeLast();
            }
         }
      }      

      JMenuItem menu = new JMenuItem(name);      //Creates the menu 
      ActionListener listener = new FaceItemListener(); 
      menu.addActionListener(listener); //Adds the listener
      return menu;
   }

   /*
    * @purpose: Creates the New Menu item and handles clicks
    * @return New menu item; the jmenu item that creates a new simulation
    */
   public JMenuItem createNewMenuItem(final String name)
   {
      class FaceItemListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
           
           startSimulator();
         }
      }      

      JMenuItem menu = new JMenuItem(name);      //Creates the menu 
      ActionListener listener = new FaceItemListener();
      menu.addActionListener(listener); //Adds the listener
      return menu;
   }
   
   /*
    * @purpose: Creates the Exit Menu item and handles clicks
    * @return Exit menu item; the jmenu item that creates a new simulation
    */
   public JMenuItem createNewExitItem(final String name)
   {

      class FaceItemListener implements ActionListener
      {

         public void actionPerformed(ActionEvent event)
         {
            
            System.exit(0);
         }
      }      

      JMenuItem menu = new JMenuItem(name);        //Creates the menu 
      ActionListener listener = new FaceItemListener();
      menu.addActionListener(listener); //Adds the listener
      return menu;
   }
   
   /*@purpose:Creates the Stack menu 
    * @return Stack Menu; the JMenu for the Stack items
    */
   public JMenuItem createNewStackItem(final String name)
   {
 
      class FaceItemListener implements ActionListener
      {

         public void actionPerformed(ActionEvent event)
         {
               if(name.equals("Push")){
                 
                 //Stack Push Code goes here
               }
               if(name.equals("Pop")){
                  
                 //Stack Pop Code goes here
               }
         }
      }
      JMenuItem menu = new JMenuItem(name);      
      ActionListener listener = new FaceItemListener();
      menu.addActionListener(listener);
      return menu;

   }
   
   
   
}