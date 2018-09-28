import static org.junit.Assert.*;


import org.junit.*;

import java.awt.Color;
//import java.io.*;

import javax.swing.JLabel;

//import org.junit.*;


public class pacmanTests {
	Token token1 = new Token(60, 60, 5, 300,300, Color.MAGENTA);
	Ghost ghost1 = new Ghost(50, 50, 2 * 1, 1 * 2,300, 300, Color.WHITE);
	Maze maze = new Maze();    
    int[][] mazeWalls;
    
    @Test
    public void updateLocation(){
    	token1.setPx(50);
    	token1.setPy(50);
    	assertEquals("X Location 50", 50, token1.getPx());
    	assertEquals("Y Location 50", 50, token1.getPy());
    }
    
    @Test
    public void readMaze(){
    	mazeWalls = readMaze.readFile("mazePlacement.txt");
    	for(int i = 0; i < mazeWalls[0].length; i++){
    		assertEquals("First Line is Wall",1, mazeWalls[i][0]);
    	}
    }
    
    @Test
    public void  createMaze(){
    	mazeWalls = readMaze.readFile("mazePlacement.txt");
    	maze.populateMaze(mazeWalls);
    	Maze.Squares [][] createdMaze = maze.getMaze();
    	for(int  i = 0; i < mazeWalls.length; i++){
    		for(int j = 0; j < mazeWalls[0].length; j++){
    			if(mazeWalls[i][j] == 0){
    				assertTrue(createdMaze[i][j].getColor().equals(Color.BLACK));	
    			}
    			else{
    				assertTrue(createdMaze[i][j].getColor().equals(Color.BLUE));
    			}
    		}
    	}
    }
    
    
    @Test
    public void invalidMaze(){
    	int [][] mazeRead  = new int[10][10];

    	mazeWalls = readMaze.readFile("whoops.txt");
    	Assert.assertArrayEquals(mazeRead, readMaze.mazeMatrix);
    }
    
    
    @Test
    public void addGhosts(){
    	GameCourt court = new GameCourt(new JLabel("Running..."));
    	for(int i = 1; i < 7; i++){
    		Ghost[] added = court.addGhosts();
    		assertEquals("Ghosts equal numLevel", i, added.length);
    		court.numLevel++;
    	}
    	
    }
    
    @Test
    public void changeLevels() {
    	GameCourt court = new GameCourt(new JLabel("Running..."));
    	court.reset();
    	int accumTokens = court.accumTokens;
    	int gatheredTokens = court.numTokens;
    	court.changeLevels();
    	assertEquals("Level Increased", 2, court.numLevel);
    	assertEquals("Ghost added", 2, court.ghosts.length);
    	assertEquals("Tokens added", (accumTokens + gatheredTokens),court.accumTokens);
    	
    }
    
    @Test
    public void addingTokens(){
    	GameCourt court = new GameCourt(new JLabel("Running..."));
    	court.reset();
    	Token[] toks = court.populateTokens();
    	int expectedTokens = 0;
    	for(int i = 0; i < court.mazeWalls.length; i++){
    		for(int j = 0; j < court.mazeWalls[0].length; j++){
    			if(court.mazeWalls[i][j] == 0){
    				expectedTokens++;	
    			}
    		}
    	}
    	int tokensAdded = 0;
    	for(int k = 0; k < toks.length; k++){
    		if(toks[k]!= null){
    			tokensAdded++;
    		}
    	}
    	assertEquals("Tokens in Every Empty Square", expectedTokens, court.actualTokens);
    	assertEquals("Tokens in array", tokensAdded, expectedTokens);
    }
    
    @Test
    public void addingScores(){
    	GameCourt court = new GameCourt(new JLabel("Running..."));
    	court.numTicks = 200;
    	court.numTokens = 100;
    	court.accumTokens = 350;
    	court.addScore();
    	assertTrue(court.highScores.containsKey((court.numTokens + court.accumTokens)));
    	assertTrue(court.highScores.get((court.numTokens + court.accumTokens)) ==  4);	
    }
    
    @Test
    public void hittingWallLeft(){
    	pacman pacman1 = new pacman(300,300, Color.YELLOW);
    	Maze maze = new Maze();
    	mazeWalls = readMaze.readFile("mazePlacement.txt");
    	pacman1.setPx(33);
    	pacman1.setVx(-4);
    	pacman1.setPy(29);
    	pacman1.setVy(0);
    	maze.populateMaze(mazeWalls);
    	Direction outcome = pacman1.checkMaze(maze);
    	assertEquals("Hit Wall", Direction.LEFT, outcome);
    }
    
    @Test
    public void mazeWallUp(){
    	pacman pacman1 = new pacman(300,300, Color.YELLOW);
    	Maze maze = new Maze();
    	mazeWalls = readMaze.readFile("mazePlacement.txt");
    	pacman1.setPx(33);
    	pacman1.setVx(0);
    	pacman1.setPy(29);
    	pacman1.setVy(-3);
    	maze.populateMaze(mazeWalls);
    	Direction outcome = pacman1.checkMaze(maze);
    	assertEquals("Hit Wall", Direction.UP, outcome);
    }
    
    
    
    

}
