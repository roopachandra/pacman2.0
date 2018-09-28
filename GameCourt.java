/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;

import java.util.TreeMap;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {


	//initialize all global variables
    private pacman pacman;
    int squareSize = COURT_WIDTH / 30;
    int[][] gamesquares = new int[30][30];
    Maze maze = new Maze();    
    int[][] mazeWalls = readMaze.readFile("mazePlacement.txt");
    int numTokens = 0;
    int numLevel = 1;
    boolean hitToken = false;
    int xtokenLocation;
	int ytokenLocation;
	Ghost[] ghosts;
	Token[] tokens;
	int posTokens;
	int actualTokens;
	int accumTokens;
	public TreeMap<Integer, Double> highScores =  new TreeMap<Integer, Double>();
	boolean firstTime;
	double numTicks;
	
    

    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static final int SQUARE_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    pacman.setVx(-SQUARE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    pacman.setVx(SQUARE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    pacman.setVy(SQUARE_VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    pacman.setVy(-SQUARE_VELOCITY);
                }
            }

            public void keyReleased(KeyEvent e) {
                pacman.setVx(0);
                pacman.setVy(0);
            }
        });

        this.status = status;
    }
    

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	pacman = new pacman(COURT_WIDTH,COURT_HEIGHT, Color.YELLOW);
        maze = new Maze();
        numLevel = 1;
        maze.populateMaze(mazeWalls);
        tokens = populateTokens();
        ghosts = addGhosts();
        playing = true;
        numTokens = 0;
        accumTokens = 0;
        status.setText("Player Score: " + numTokens + "     Level: " + numLevel);
        firstTime = true;
        numTicks = 0;

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    public Token[] populateTokens(){
    	Maze.Squares[][] colorSquares = maze.getMaze();
    	posTokens = colorSquares.length * colorSquares[0].length;
    	Token[] tok = new Token[posTokens];
    	actualTokens = 0;
    	for(int j = 0; j < colorSquares.length; j++){
    		for(int k = 0; k < colorSquares[0].length; k++){
    			if(colorSquares[j][k].getColor().equals(Color.BLACK)){
    				int xlocation = colorSquares[j][k].getXPos() + 15;
    				int ylocation = colorSquares[j][k].getYPos() + 15;
    				tok[actualTokens]  = new Token(xlocation, ylocation, 5, COURT_WIDTH,
    						COURT_HEIGHT, Color.MAGENTA);
					actualTokens++;
    				
    			}
    		}
    	}
    	return tok;
    }
    
    public Ghost[] addGhosts(){
    	ghosts = new Ghost[numLevel];
    	int xstartLoc = 60;
    	int ystartLoc = 60;
    	for(int i = 0; i < numLevel; i++){
    		xstartLoc = (int) (285 * Math.random());
    		ystartLoc = (int) (285 * Math.random());
    		ghosts[i] = new Ghost(ystartLoc, xstartLoc, 2 * numLevel, numLevel * 2,
    				COURT_WIDTH, COURT_HEIGHT, Color.WHITE);
    		
    	}
    	return ghosts;
    }
    
    public void changeLevels(){
    	numLevel++;
    	accumTokens = numTokens + accumTokens;
        numTokens = 0;
    	tokens = populateTokens();
        ghosts = addGhosts();
        playing = true;
        status.setText("Player Score: " + numTokens + "     Level: " + numLevel);
    }
    
    public void addScore(){
    	firstTime = false;
    	highScores.put((numTokens + accumTokens), (numTicks/50));
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	numTicks++;
          
            pacman.move();

            pacman.mazeWall(pacman.checkMaze(maze));;

            for(int j = 0; j < ghosts.length; j++){
            	ghosts[j].move();
            	ghosts[j].bounce(ghosts[j].hitWall());
            }
            for(int i = 0; i < actualTokens; i++){
            	if(pacman.intersects(tokens[i])){
            		tokens[i].display = false;
            		hitToken = true;
            		xtokenLocation = pacman.getPx();
            		ytokenLocation = pacman.getPy();
            		break;
            	}
            }
            if(numTokens == actualTokens){
            	changeLevels();
            }
            for(int k = 0; k < ghosts.length; k++){
            	if(pacman.intersects(ghosts[k])){
            		playing = false;
            		status.setText("You Lose!");
            	}
            }
            repaint();
        }
        else{
        	if(firstTime){
        		addScore();
        		//System.out.println(highScores.toString());
        	}
        	
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.draw(g);
        //snitch.draw(g);
        pacman.draw(g);
        int tokensLeft = 0;
        for(int j = 0; j < ghosts.length; j++){
        	ghosts[j].draw(g);
        }
        for(int i = 0; i < actualTokens; i++){
        	if(tokens[i].display){
        		tokens[i].draw(g);
        		tokensLeft++;
        		
        	}
        	numTokens = (actualTokens - tokensLeft);
        }
        status.setText("Player Score: " + (numTokens + accumTokens)+ "     Level: " + numLevel);
        
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}