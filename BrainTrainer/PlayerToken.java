//imports
import java.awt.*;

/*
    @purpose: subclass of game token which creates a user token which is always visible
 */
public class PlayerToken extends GameToken {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final Color COLOR = Color.BLUE;

    /*
        @purpose:Constructor,makes the token
        @param x, the x location of the token
        @param: y, the y location of the token
     */
    public PlayerToken(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        super.setColor(COLOR);
        setVisibilityPolicy();
    }

    //@purpose: Updates the location of the user token
    //@param x,y the current x and y locations of the token
    public void update(int x, int y) {
        Rectangle rect = new Rectangle(x, y, WIDTH, HEIGHT);


        int type = super.getPattern().getType();

        super.update(type, rect);
    }

    //@purpose:Cycles through pattern types and sets them
    public void nextType() {
        super.getPattern().chooseType();
    }

    //@purpose: gets rectangle
    public Rectangle getBBox() {
        return super.bbox;
    }

    //@purpose:Draws the user token
    public void draw(Graphics g) {
        g.setColor(COLOR);
        super.draw((Graphics2D) g);
    }

    //@purpose:Makes token always visible
    @Override
    public void setVisibilityPolicy() {
        super.setVisibility(true);
    }

}
