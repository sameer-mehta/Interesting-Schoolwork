
/*
 * @purpose:Superclass for rail car and train engine
 */
import java.awt.Rectangle ;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public abstract class Vehicle
{
   
   protected Point2D.Double frontPoint;
   protected Point2D.Double rearPoint;
   protected Line2D.Double hitch;
   protected int xLeft;
   protected int yTop;
   protected int xCursorDifference;
   protected int yCursorDifference;
   protected final int HEIGHT = 30;
   protected final int WIDTH = 75;
   protected boolean isSelected; 
   protected Rectangle rect;
   
   
   public Vehicle next;
   
   
  //@purpose:Deafult Constructor
  //Creates a new Vehicle
   public Vehicle()
   {
      xLeft=0;
      yTop=0;
      rect = new Rectangle(xLeft, yTop, HEIGHT, WIDTH);
      frontPoint = new Point2D.Double(0, 0);
      rearPoint = new Point2D.Double(0, 0);
      next = null;
   }

   //@purpose: Constructor that creates a vehicle at given xLeft and yTop 
   //@param x;the x value
   //@param y; the y value
   public Vehicle(int x, int y)
   {
      next = null;
      x = x;
      y = y;
      frontPoint = new Point2D.Double(x, y+10);
      rearPoint = new Point2D.Double(x+WIDTH-10, y+10);
      rect = new Rectangle(x, y, HEIGHT, WIDTH);
   }

 //@purpose:Draws the vehicle
   public void draw(Graphics2D g2)
   {
      if(isSelected)
         g2.setColor(Color.RED);
      else
         g2.setColor(Color.BLACK);
      rect = new Rectangle(xLeft, yTop, WIDTH, HEIGHT);
     
      frontPoint.setLocation(xLeft, yTop+20);
      rearPoint.setLocation(xLeft+WIDTH-15, yTop+20);

      if(next!=null){
         next.setPosition(xLeft+WIDTH, yTop);
         next.draw(g2);
         g2.draw(new Line2D.Double(rearPoint, next.getFrontPoint())); //Draws the hitch between the linked list Vehicles.
      }
   }
   
   //@purpose:Sets the next vehicle in the list
   //@param Vehicle v; the next vehicle
   public void setNext(Vehicle v)
   {
      if(next!=null){
         next.setNext(v);
      }
      else 
         next = v;
   }
   
   //@purpose:Prevents Vehicles from being drawn on top of each other by moving the location slightly
   public void setNextPosition()
   {
      if(next!=null){
         next.setNextPosition(xLeft+WIDTH, yTop);
      }
   }

   //@purpose: Sets the next position at a given xLeft and yTop
   //@param x;the x value
   //@param y; the y value
   public void setNextPosition(int x, int y)
   {
      x=x;
      y=y;
      if(next!=null){
         next.setNextPosition(x+WIDTH, y);
      }
   }

   //@purpose:Checks if the point is in the rectangle rect
   //@param x;the x value
   //@param y; the y value
   public boolean contains(int x, int y)
   {
      if(rect.contains(x,y))
         return true;
      return false; 
   } 
   
   //@purpose:Checks if a vehicle interesects another
   //@param: Vechile v;the other vehicle
   public boolean intersects(Vehicle v)
   {
      if(rect.intersects(v.getRect()))
         return true;
      else 
         return false;
   }

   //@purpose: Used to obtain the current xLeft value
   //@return xLeft; the xLeft value
   public int getX()
   {
      return xLeft;
   }

   //@purpose: Used to obtain the current yTop value
   //@return yTop; the yTop value
   public int getY()
   {
      return yTop;
   }

   //@purpose: Gets the coordinates 
   //@return string; the coordinates
   public String coordinates()
   {
      return ("("+xLeft+","+yTop+")");
   } 

  //@purpose: Sets the coordinates 
  public void setPosition(int x, int y)
   {
      xLeft = x;
      yTop = y;
   }
   
   //@purpose: Sets the coordinate 
  //@param x;the x value
  //@param y; the y value
   public void setCursorDifference(int x, int y)
   {
      xCursorDifference = x;
      yCursorDifference = y;
   }

   //@purpose: return the x cursor location
   //@return xCursorDifferenece; the current x value at the selected point
   public int getXCursorDifference()
   {
      return xCursorDifference;
   }
 
   //@purpose: return the y cursor location
   //@return yCursorDifferenece; the current y value at the selected point
   public int getYCursorDifference()
   {
      return yCursorDifference;
   }
  
   
   
   public Rectangle getRect()
   {
      return rect;
   }

   //@purpose: Returns a string represantation of the x and y vlaue and the next vehicle
   //@return string; string of of the x and y vlaue and the next vehicle
   public String toString()
   {
      return ("Vehicle["+xLeft+", "+yTop+"] Next: "+next);
   }

   //@purpose: Makes a vehicle selected
   public void setSelected()
   {
      isSelected = true;
      setNextSelected();
   }
  
   //@purpose:Makes the next vehicle selected
   public void setNextSelected()
   {
      isSelected=true;
      if(next!=null){
         next.setNextSelected();
      }
   }
  
   //@purpose:Makes the next vehicle unselected
   public void setNextUnSelected()
   {
      if(next!=null){
         next.setNextUnSelected();
      }
      isSelected=false;
   }
 
   //@purpose:Makes the current vehicle selected
   public void setUnSelected()
   {
      isSelected = false;
   }
 
   //@purpose:Removes the next vehicle
   public void removeNext()
   {
      next = null;
   }

   //@purpose:Checks if there is another vehicle after this one
   public boolean hasNext()
   {
      return !(next==null);
   }

   //@purpose: Gets the last vehicle in the list
   //@return Vehicle last; the last vehicle in the lsit
   public Vehicle getLast()
   {
      if(next==null){
         
         return this;
      }
      else
         return next.getLast();
   }

   //@purpose:Checks if there is this vechile is selected
   //@return Boolean based on if it is selected or not
   public boolean isSelected()
   {
      return isSelected;
   }

   //@purpose: Gets the next vehicle
   //@return Vehicle next; the next vehicle
   public Vehicle getNext()
   {
      return next;
   }

   public int getWidth()
   {
      return WIDTH;
   }

   public Point2D getFrontPoint()
   {
      return frontPoint;
   }
}