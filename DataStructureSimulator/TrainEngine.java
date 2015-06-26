
/*
 * @purpose: Subclass of Vehicle; Used to draw the train engine
 */
import java.awt.Rectangle ;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class TrainEngine extends Vehicle
{

 
   private static final double WIDTH = 35 ;
   private static final double UNIT = WIDTH / 7 ;
   private static final double LENGTH = 14 ; 
   private static final double HEIGHT = 8 ; 
   private static final double U_1 = 0.5 * UNIT ; 
   private static final double U_3 = 1.5 * UNIT ; 
   private static final double U2_5 = 2.5 * UNIT ; 
   private static final double U3 = 3 * UNIT ; 
   private static final double U4 = 4 * UNIT ; 
   private static final double U5 = 5 * UNIT ; 
   private static final double U10 = 10 * UNIT ; 
   private static final double U9_1 = 9.1 * UNIT ; 
   private static final double U10_1 = 10.1 * UNIT ; 
   private static final double U11_1 = 11.1 * UNIT ; 
   private static final double U14 = 14 * UNIT ; 
   private Rectangle2D.Double hood;
   private Rectangle2D.Double body;
   private Ellipse2D.Double wheel1;
   private Ellipse2D.Double wheel2;
   private Ellipse2D.Double wheel3;
   private Ellipse2D.Double wheel4;
   private Ellipse2D.Double wheel5;
   private Ellipse2D.Double wheel6;
   ArrayList<RectangularShape> list;

 
   //@purpose:Draws the train engine at a given x and y
   public TrainEngine(int x, int y)
   {
      super();
      xLeft = x;
      yTop = y;
      list = new ArrayList<RectangularShape>();
      list.add(hood);
      list.add(body);
      list.add(wheel1);
      list.add(wheel2);
      list.add(wheel3);
      list.add(wheel4);
      list.add(wheel5);
      list.add(wheel6);
      
   }

 
 //@purpose:Draws the train engine 
   public void draw(Graphics2D g2)
   {
      super.draw(g2);
      int x1 = xLeft;
      int y1 = yTop;
      g2.setColor(Color.BLACK);
      hood = new Rectangle2D.Double(x1, y1 + UNIT, 
            U3, U3 ) ;
      g2.setColor(Color.blue) ;
      g2.fill(hood) ;

      body = new Rectangle2D.Double(x1 + U3,y1-5,
            U10, U4+5) ;
      g2.setColor(Color.blue) ;
      g2.fill(body) ;


      wheel1 = new Ellipse2D.Double(x1 + U_1, 
            y1 + U4, 
            UNIT, UNIT) ;
      g2.setColor(Color.black) ;
      g2.fill(wheel1) ;

      wheel2 = new Ellipse2D.Double(x1 + U_3, 
            y1 + U4, 
            UNIT, UNIT) ;
      g2.setColor(Color.black) ;
      g2.fill(wheel2) ;

      wheel3 = new Ellipse2D.Double(x1 + U2_5, 
            y1 + 4 * UNIT, 
            UNIT, UNIT) ;
      g2.setColor(Color.black) ;
      g2.fill(wheel3) ;

      wheel4 = new Ellipse2D.Double(x1 + U9_1, 
            y1 + U4, 
            UNIT, UNIT) ;
      g2.setColor(Color.black) ;
      g2.fill(wheel4) ;

      wheel5 = new Ellipse2D.Double(x1 + U10_1, 
            y1 + U4, 
            UNIT, UNIT) ;
      g2.setColor(Color.black) ;
      g2.fill(wheel5) ;
      
      wheel6 = new Ellipse2D.Double(x1 + U11_1, 
              y1 + U4, 
              UNIT, UNIT) ;
        g2.setColor(Color.black) ;
        g2.fill(wheel6) ;
      if(isSelected)
         g2.setColor(Color.RED);
      if(next != null)
         next.draw(g2);
   }

   public String toString()
   {
      return ("TrainEngine["+xLeft+","+yTop+"]-> "+next);
   }
}