package a2;
/*
 * @purose:Subclass of Vehicle;Used to draw the railcars
 */
import java.awt.Rectangle ;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class RailCar extends Vehicle
{
   public int identifier;

   public RailCar()
   {
      super();
      identifier = 0; 
   }

   public RailCar(int x, int y)
   {
      super(x, y);
      identifier = 0;
   }

  //@purpose:Draws the rail car
   public void draw(Graphics2D g2)
   {
      super.draw(g2);
      g2.drawString(Integer.toString(identifier), xLeft+25, yTop);
      Rectangle body 
         = new Rectangle(xLeft, yTop + 10, 60, 10);      
      Ellipse2D.Double frontTire 
         = new Ellipse2D.Double(xLeft + 10, yTop + 20, 5, 5);
      Ellipse2D.Double rearTire
         = new Ellipse2D.Double(xLeft + 40, yTop + 20, 5, 5);

      g2.draw(body);
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.fill(frontTire);
      g2.fill(rearTire);
          
        
   }

   //@purose:Sets the number
   public void setValue(int value)
   {
      identifier = value;
      
   }
   
   public String toString()
   {
      return ("Car"+identifier+"["+xLeft+","+yTop+"]-> "+ next);
   }
}