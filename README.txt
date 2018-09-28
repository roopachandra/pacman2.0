=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: roopac
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Array - I implemented a 2D array to store the layout of the level. My game maintains and updates
  a 2D array that contains a type called squares. The color of the square (blue or black) indicates
  whether the square is part of the free space or part of the maze walls. I utilized a 2D array
  because this way I can represent the entire 10 squares by 10 squares level in an array of length 10
  10. Therefore, when implementing other features such as the pacman, it is easy to directly access
  the square in the current location and determine if it is a wall of not. 

  2.Collections - I used a TreeMap to store the past scores of the user. The key is the number of
  tokens collected and the value is the time it took the user to rack up that score. I used a TreeMap
  for a couple of different reasons. To begin with, a treeMap allows me to add many scores and not have
  a fixed size like an array. It also allows me to link both the number of tokens and time in the
  memory. Lastly, TreeMaps automatically store key,value pairs in a sorted order so when displaying
  the high scores, the map has already sorted the scores in ascending order.

  3. I/0 - I used IO to read in the layout of the maze for the game. I provide a file that consists
  of 0's and 1's and then my game converts 0's to free space and 1's to maze walls. I decided to allow
  the maze layout to be read in so the layout can change if the user wants to try a different maze.
  This allows the game to be dynamic and change if a new file is provided. Therefore, while
  implementing this, I had to account for different locations of maze walls so my methods such as
  adding the tokens to the maze are coded to find the current maze walls and work around them as
  opposed to hard coding the tokens in place.  

  4. JUnit Testing - I created tests for all my methods I created in my Game Court such as adding
  tokens, adding ghosts, changing levels, adding scores, etc. I tested these methods to determine
  if my code was correctly updating the state of the game during certain events. For example, I
  tested that the game state was updated to include an additional ghost when the next level is
  reached. Similar to the tests we created to check the server model in Homework 7, I made certain
  events occur and then tested whether the game updated as I had intended. I also tested several of
  the methods that the Game Court calls such as reading in a file. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
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
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  This biggest challenge I came across while creating this game was creating the maze walls. I
  figured out relatively quickly how to read in a file and display the maze graphically in the
  window. However, I became stuck when I was trying to make the walls impassable. In other words,
  I was stumped on how to make the pacman stop at the blue squares but still be able to go over
  the black squares. It took me time to realize I could not simply check if the pacman's location
  was in a blue square or a black square. I had to account for velocity of the pacman and account
  for the width and height of the pacman. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  Overall, the program has good style and separation of functionality. For example, the fields of
  all game objects are private and can only be accessed through get methods. However, if I were to 
  re-do the project, I would seperate some of the functionality in the GameCourt. I have several
  methods in GameCour that perform radically different operations. Therefore, it works well in
  updating the state of the game but leads to a large file that allows access to various variables. 
  Instead, I would have put the functions in various different classes. 



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

I did not use any external resources besides the documentation of java data structures and methods
provided by the Oracale Help Center at https://docs.oracle.com/javase/7/docs/api/overview-summary.html

