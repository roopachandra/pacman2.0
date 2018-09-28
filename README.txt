=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
Pacman 2.0
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

=========================
=: Implementation :=
=========================
  
  Game - This is where all the components of the game are put together and the GUI interface is
  created through a JFrame. It sets up the initial window for the game to display and starts the
  game.
  
  GameCourt  - This is where the heart of the program lays. It contains the central function tick
  that is continually called which allows the program to update the game's state according to the
  user's actions. In addition, it contains code that deals with changing the board when a new
  level is reached, adding tokens to the level, keeping track of scores, and adding the appropriate
  number of ghosts traveling the correct speed. 
  
  GameObj  - This abstract class implements some of the basic functions that all the different
  game objects use. GameObj is extended by all the objects in the game such as Token, pacman, and
  ghost. It has functions such as setting and getting the object's location, velocity, and color. In 
  addition GameObj also includes functions that handle interactions between two gameObjects such as
  intersections and checkMaze which prevents a pacman from going through the maze walls. 
  
  Ghost - This class extends GameObj and contains the draw method that visually displays the circles
  which represent the ghost. 
  
  Token - This class extends GameObj and contains a draw method that visually displays the small
  purple circle that represents the token.  
  
  PacMan - This class extends GameObj and contains a draw method that visually displays the yellow
  circle that represents the pacman. 
  
  Instruction - This class contains the code the visually displays the HomePage. It creates a new
  JFrame and displays the scores on the page. 
  
  ReadMaze - This class reads in a file containing 0's and 1's that then creates a 2D array
  representation of the file. This array is then later used to create the maze. 
  
  Direction -This class holds an enumeration called Direction, which is used in GameObj.java to 
  indicate the direction an object should move after it collides with another object.
  
  Maze - This class contains the method that takes the matrix created when reading in the file and
  visually displays the maze. In addition, the maze contains a subclass called squares which
  is how the maze is created. Essentially, the maze is a 2D array of squares that are blue if the
  matrix had a 1 in that location. 
  
  pacManTests - This file contains JUnit tests that I wrote that tests if the game state is
  correctly updated when certain events occur. It tests GameCourt to ensure that the methods
  such as adding tokens to the maze and changing the ghosts for each level update the state
  of the game correctly. 
  


