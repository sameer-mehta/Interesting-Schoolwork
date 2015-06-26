
//imports
import javax.swing.*;
/*
    @author: Sameer Mehta
    @since 2015-03-12
    @version: 1.0
    @purpose: A concentration/memory game(n-back game)
 */

/*Brain Trainer main class
  @purpose: Creates Jframe that holds Game panel and score panel
*/
public class BrainTrainer extends JFrame {

    // @purpose: Creates JFrame
    public static void main(String[] args) {

        //@purpose: Jframe that holds game and score panels
        JFrame frame = new JFrame();

        //setting jframe title
        frame.setTitle("Brain Trainer");

        //makes application close when close button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets layout
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //adds the score panel which shows the time and the score
        frame.add(new ScorePanel());

        //adds the game panel which holds the tokens
        frame.add(new GamePanel());

        //set window size
        frame.setSize(600, 700);

        //prevents the jframe from being resized
        frame.setResizable(false);

        //makes the jframe visible
        frame.setVisible(true);

        GamePanel g = new GamePanel();
        
   



    }
}


