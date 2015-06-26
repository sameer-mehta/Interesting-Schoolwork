//imports
import java.awt.*;
import java.util.Random;

/*
    @purpose: Generates patterns for tokens
 */
public class Pattern {
    // Some example patterns
    public static final int CROSS = 0;
    public static final int CIRCLEPLUS = 1;
    public static final int SQUARE_X = 2;
    private static final int NUM_PATTERNS = 3;

    private int type;
    private int x, y, width, height;

    /*
        @purpose;Constructor, generates a random pattern for the token
        @param bbox,the rectangle to be used for the token
     */
    public Pattern(Rectangle bbox) {

        Random rand = new Random();
        type = rand.nextInt(3);
        height = bbox.height;
        width = bbox.width;
        x = bbox.x;
        y = bbox.y;

    }

    /*
        @purpose:Constructor, creates a token with a given pattern type
        @param: type,the desired pattern type
     */
    public Pattern(int type, Rectangle bbox) {

        this.type = type;
        height = bbox.height;
        width = bbox.width;
        x = bbox.x;
        y = bbox.y;

    }

    /*
        @purpose:Method returns the pattern type
        @returns type,the pattern type
     */
    public int getType() {
        return type;
    }

    //@purpose: Sets the pattern type to a desired pattern
    public void setType(int type) {
        this.type = type;
    }

    /*
        @purpose: Cycles through pattern types
        @return type, the pattern type
     */
    public int chooseType() {
        type = ((type + 1) % NUM_PATTERNS);
        return type;
    }

    /*
        @purpose: Updates the type,location and size of the token
     */
    public void update(int type, Rectangle bbox) {

        height = bbox.height;
        width = bbox.width;
        x = bbox.x;
        y = bbox.y;
        this.type = type;
    }

    /*
     @purpose: checks if user token is equal to a game token
     @returns boolean(true or false), depending on if the pattern and colour was the same
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }


        if (!(obj instanceof Pattern)) {
            return false;
        }

        Pattern otherpattern = (Pattern) obj;

        return (otherpattern.height == this.height && otherpattern.type == this.type && otherpattern.width == this.width && otherpattern.x == this.x && otherpattern.y == this.y);

    }

    /*
        @purpose:Draws the tokens with the desired patterns
     */
    public void draw(Graphics2D g2) {
        // add code
        if (this.type == 0) {
            g2.drawLine(x + (width / 2), y, x + (width / 2), y + height);
            g2.drawLine(x, y + (height / 2), x + width, y + (height / 2));
        } else if (this.type == 1) {
            g2.drawOval(x, y, width, height);
            g2.drawLine(x + (width / 2), y, x + (width / 2), y + height);
            g2.drawLine(x, y + (height / 2), x + width, y + (height / 2));
        } else {
            g2.drawLine(x, y, x + width, y + height);
            g2.drawLine(x + width, y, x, y + height);
        }


    }
}
