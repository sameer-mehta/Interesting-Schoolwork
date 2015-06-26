//imports
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

//@purpose: Game token superclass that implements the visible shape methods
public class GameToken implements VisibleShape {

    public Rectangle bbox;
    private boolean visible;
    private final Pattern pattern;
    private Color color;
    private static final int TIMEOUT = 3;    //initial time out in seconds
    Color c= color.red;
    /*@purpose: Default constructor;generates a token with a random pattern type
    @param x; the x location of the token
    @param y; the y location of the token
    @param width; the desired width of the token
    @param height; the desired height of the token
    */
    protected GameToken(int x, int y, int width, int height) {

        //initializes the rectangle
        bbox = new Rectangle(x, y, height, width);


        //Initializes the pattern with a random pattern
        pattern = new Pattern(bbox);

        //Make it visible
        visible = true;


        color = Color.BLACK;
    }

    /*@purpose: Constructor; generates a token with a given pattern type
    @param patterntype; the desired pattern type
    @param x; the x location of the token
    @param y; the y location of the token
    @param width; the desired width of the token
    @param height; the desired height of the token
    */
    protected GameToken(int patterntype, int x, int y, int width, int height) {

        //Initializes the rectangle
        bbox = new Rectangle(x, y, height, width);

        //Initializes the pattern with a given pattern
        pattern = new Pattern(patterntype, bbox);

        //Makes it visible
        visible = true;


        color = Color.BLACK;
    }

    /*
        @purpose: Returns whether the token is visible to not
        @returns visible; the visibility of the token
     */
    protected boolean getVisibility() {
        return visible;
    }

    /*
        @purpose: Sets the visibility
     */
    protected void setVisibility(boolean visible) {
        this.visible = visible;
    }

    /*
        @purpose: Returns the colour
        @returns color;the color of the token
     */
    protected Color getColor() {
        return color;
    }

    /*
        @purpose: Sets the colour
    */
    protected void setColor(Color color) {
        this.color = color;
    }

    /*
        @purpose: Returns the pattern type
        @returns pattern; the pattern type of the token
     */
    public Pattern getPattern() {
        return pattern;
    }

    /*
       @purpose: Compares the pattern types
       @returns boolean,depending on whether the patterns s the were the same or not
    */
    public boolean comparePattern(GameToken gametoken) {
        return this.getPattern().getType() == gametoken.getPattern().getType();
    }

    /*
       @purpose: Updates the pattern type and token properties
    */
    public void update(int type, Rectangle bbox) {
        this.bbox = bbox;
        this.pattern.update(type, this.bbox);

    }

    //@purpose: Makes the token visible again
    public void reset() {
        visible = true;
    }

    /*
     @purpose: checks if user token is equal to a game token
     @returns boolean(true or false), depending on if the pattern and colour was the same
     */
    public boolean equals(Object other) {

        // If the object is compared with itself then return true
        if (other == this) {
            return true;
        }

        if (!(other instanceof GameToken)) {
            return false;
        }


        GameToken othertoken = (GameToken) other;


        return (bbox.equals(othertoken.bbox) && pattern.equals(othertoken.getPattern()) && this.color == othertoken.getColor());
    }

    //@purpose:Draws the token
    @Override
    public void draw(Graphics2D g2) {
    	g2.setColor(c);
    	g2.draw(bbox);
        if (visible){
        	
        	pattern.draw(g2);
        }
    }

    /*
        @pupose:Checks for overlaps and returns boolean(true of false)
        @returns true or false,depending on if there was an intersection or if they were the same
     */
    @Override
    public boolean overlaps(VisibleShape other) {
        if (other instanceof GameToken) {
            Rectangle temp = ((GameToken) other).bbox;
            return temp.intersects(bbox);
        } else {
            return true;
        }

    }

    //@purpose: Sets the visibility and controls how long a token is displayed for.
    @Override
    public void setVisibilityPolicy() {

        setVisibility(true);

        //set a timeout that sets the visibility to false after TIMEOUT seconds
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisibility(false);
            }
        }, TIMEOUT * 1000);
    }





}
