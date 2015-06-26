//imports
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//@purpose:game panel holds all the tokens and handles all the clicks
class GamePanel extends JPanel {

    private final int NUMBERTOKENS = 10;
    private final int XSIZE = 400; //sets width of jpanel
    private final int YSIZE = 500; //sets height of jpanel
    private final int REFRESHINTERVAL = 10; //refreshes jpanel every 10 milliseconds
    private final int REFRESHDELAY = 10;
    private final Dimension SIZE;    //holds size of the panel

    //tokens
    MemoryToken MemoryTokens[];
    private ArrayList<MemoryToken> tokens;
    private PlayerToken playertoken;
    private boolean tokenFound[];    //stores the status of each memory token
    private Random rand;
    private int tries[] = new int[NUMBERTOKENS]; //stores numbers of unsuccessful tries for each token
    private static int matched; //variable used to check if all tokens have been matched
    public static int count=0;

    //Default constructor
    //@purpose: creates jpanel
    public GamePanel() {
    	
        //initialize to the desired x and y size
        SIZE = new Dimension(XSIZE, YSIZE);

        //sets the panel size
        setPreferredSize(SIZE);

        //sets background colour of jpanel
        setBackground(Color.WHITE);

        //initialize the refresh timer
        refreshPanel(REFRESHDELAY, REFRESHINTERVAL);

        //add the tokens to the panel
        addTokens();



        //Initialize array to token as no tokens have been found yet
        tokenFound = new boolean[NUMBERTOKENS];
        for (int i = 0; i < NUMBERTOKENS; i++) {
            tokenFound[i] = false;
        }
        //add the eventlisteners
        
        addMouseListener(new MouseAdapter() {

            //mouse pressed handles left and right clicks
            public void mousePressed(MouseEvent e) {


                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (playertoken == null) {

                        //if clicked for the first time, create the player token and redraw
                        playertoken = new PlayerToken(e.getX() - 15, e.getY() - 15);

                        repaint(e.getX() - 15, e.getY() - 15, 31, 31);
                    } else {


                        Rectangle temp = playertoken.getBBox();

                        playertoken.update(e.getX() - 15, e.getY() - 15);

                        //repaint new area
                        repaint(e.getX() - 15, e.getY() - 15, 31, 31);

                        //erase old area
                        repaint(temp.x, temp.y, temp.width + 1, temp.height + 1);
                    }

                    //for loop to check whether all tokens have been matched
                    for (int i = 0; i < tokens.size(); i++) {

                        //is statement checks if user token overlaps a game tokens
                        if (playertoken.overlaps(tokens.get(i))) {

                            //if matched on the first try
                            if (playertoken.comparePattern(tokens.get(i)) && tokenFound[i] != true) {
                                tokens.get(i).setVisibility(true);
                                tokens.get(i).c = (Color.GREEN);
                                tokens.get(i).setColor(Color.GREEN);
                                //repaint new location
                                repaint(e.getX() - 15, e.getY() - 15, 31, 31);
                                System.out.println("Matched");
                                tokenFound[i] = true;
                                int score = ScorePanel.getScore();
                                score = score + 2;
                                ScorePanel.setScore(score); //awards 2 points
                                matched++;
                                count++;
                                if(count>9)System.exit(0);
                                
                            }

                            //if user has already had an unsuccessful try
                            //awards only one point
                            else if(tries[i]==1 && playertoken.comparePattern(tokens.get(i)) && tokenFound[i] != true)   {
                                tokens.get(i).setVisibility(true);
                                tokens.get(i).setColor(Color.GREEN);
                                //repaint new area
                                repaint(e.getX() - 15, e.getY() - 15, 31, 31);
                                tokenFound[i] = true;
                                System.out.println("Matched.But you only get 1 point because it's your second try.");
                                int score = ScorePanel.getScore();
                                score++;
                                ScorePanel.setScore(score);
                                matched++;
                                count++;
                                if(count>9)System.exit(0);
                            }

                            //matched after 2 unsuccessful try.
                            //awards no points
                            else if(tries[i]==2  && playertoken.comparePattern(tokens.get(i))&& tokenFound[i] != true) {
                                tokens.get(i).setVisibility(true);
                                tokens.get(i).setColor(Color.GREEN);
                                //repaint new area
                                repaint(e.getX() - 15, e.getY() - 15, 31, 31);
                                tokenFound[i] = true;
                                System.out.println("Matched.But you get no points because of multiple tries.");
                                matched++;
                                count++;
                                if(count>9)System.exit(0);
                            }

                            //if user matches after 2 unsuccessful tries
                            //User loses 2 points
                            else if(tries[i]>2 && ScorePanel.getScore()>0 && !playertoken.comparePattern(tokens.get(i)))
                            {

                                    System.out.println("Not matched. You lose 1 point.");
                                    int score = ScorePanel.getScore();
                                    score--;
                                    ScorePanel.setScore(score);

                                }

                            //increments tries for every time the token is attempted unsuccessfully
                            else if (!playertoken.comparePattern(tokens.get(i)))
                            {
                                    System.out.println("Not matched. Try again.");
                                    tries[i]++;
                            }


                        }
                    }


                }

                //right click
                //allows user to choose between patterns
                else if (SwingUtilities.isRightMouseButton(e)) {


                    if (playertoken == null) {
                        playertoken = new PlayerToken(e.getX() - 15, e.getY() - 15);

                        repaint(e.getX() - 15, e.getY() - 15, 31, 31);
                    } else {
                        Rectangle temp = playertoken.bbox;

                        //update the location and change the type
                        playertoken.update(e.getX() - 15, e.getY() - 15);
                        playertoken.nextType();

                        //repaint new area
                        repaint(e.getX() - 15, e.getY() - 15, 31, 31);

                        //repaint old area
                        repaint(temp.x, temp.y, temp.width + 1, temp.height + 1);

                    }

                }



            }


        });


        setVisible(true);



    }



    //@purpose: Creates 10 tokens with random patters and adds them to the tokens array
    private void addTokens() {
        tokens = new ArrayList<MemoryToken>(NUMBERTOKENS);
        int count_tokens = 0;
        while (count_tokens < NUMBERTOKENS) {
            int flag = 1;
            MemoryToken memorytoken = randomMemoryToken();
            for (MemoryToken token : tokens) {
                if (memorytoken.overlaps(token)) {
                    flag = 0;
                    break;
                } else flag = 1;
            }
            if (flag == 1) {
                tokens.add(memorytoken);
                count_tokens++;

            }
        }
    }

    //@purpose: generates a memory token with a random pattern type
    private MemoryToken randomMemoryToken() {

        rand = new Random();

        //makes sure the token stays in the panel
        int x = rand.nextInt(XSIZE - 50);
        int y = rand.nextInt(YSIZE - 80);

        return new MemoryToken(x, y);
    }

    //@purpose: repaints the entire panel, starting after a delay, at each interval
    private void refreshPanel(int delay, int interval) {

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint(0, 0, XSIZE, YSIZE);
            }
        }, delay, interval);
    }

    //@purpose: used to paint tokens
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, XSIZE, YSIZE);

        super.paintComponent(g);
        for (int i = 0; i < NUMBERTOKENS; i++)
            tokens.get(i).draw(g);

        if (playertoken != null)
            playertoken.draw(g);
    }


    public static Boolean getMatched(){
        return (matched == 10);
    }



}



