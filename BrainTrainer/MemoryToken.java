//imports
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

//@purpose: Subclass of game token class which is used to create tokens that the user interacts with
public class MemoryToken extends GameToken {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final Color COLOR = Color.RED;
    private static final int TIMEOUT = 3;    //initial time out in seconds

    /*
        @purpose: Constructor, creates a memory token given location
        @param x,the x location of the token
        @param y,the y location of the token
     */
    public MemoryToken(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        super.setColor(COLOR);
        setVisibilityPolicy();
    }

    //@purpose:Implements setVisiblityPolicy method og Visible Shape interface
    //Makes the token appear visible for the desired number of time and then makes it invisible
    @Override
    public void setVisibilityPolicy() {

        setVisibility(true);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisibility(false);
            }
        }, TIMEOUT * 1000);
    }

    //@purpose:Draws the token
    public void draw(Graphics g) {
        g.setColor(COLOR);
        super.draw((Graphics2D) g);
    }
}
