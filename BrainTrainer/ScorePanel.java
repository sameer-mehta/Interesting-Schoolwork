
//imports
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

//@purpose:Class that draws a Heads up display which shows the score and time
class ScorePanel extends JPanel {

    //scorecount keeps the score
    private static  int scorecount;
    //Dimensions of the jpanel are stored in this
    private final Dimension DIMENSIONS;
    //label which shows the time
    private final JLabel lbltime;
    //label which shows the score
    private final JLabel lblscore;

    private final Timer timer;
    private int timercount;

    //constructor which makes the panel
    public ScorePanel() {


        //initializes the dimensions
        DIMENSIONS = new Dimension(300, 30);

        //set jpanel size
        this.setPreferredSize(DIMENSIONS);

        //set panel layout
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        //initialize label, set it to be at the top left, and make it visible
        lbltime = new JLabel();
        lbltime.setAlignmentX(0);
        lbltime.setAlignmentY(0);
        lbltime.setVisible(true);

        scorecount = 0; //initialize variable

        //initialize label, set the text and make it visible
        lblscore = new JLabel();
        lblscore.setText("Score:");
        lblscore.setVisible(true);

        //add the labels to the jpanel
        add(lbltime);
        add(lblscore);

        //Initialize timer and  timercount
        timer = new Timer();
        timercount = 1;

        //updates labels with score and time
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                lbltime.setText("Time: " + timercount);
                timercount++;
                lblscore.setText("Score:" + scorecount);

            }
        }, 1000, 1000);


    }

    //@purpose:Updates the score
    //@param score; the score that is to be set
    public static void setScore(int score) {
        scorecount = score;
    }

    //@purpose:Gets the current score
    //@return scorecount;the current score
    public static int getScore() {
        return scorecount;
    }

}