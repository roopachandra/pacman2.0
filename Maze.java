import java.awt.Color;
import java.awt.Graphics;

public class Maze {
	public int xWidth = GameCourt.COURT_WIDTH / 30;
	public int yWidth = GameCourt.COURT_HEIGHT / 30;
	private Squares[][] maze = new Squares[xWidth][yWidth];

	public Squares[][] getMaze(){
		return maze;
	}

	class Squares {
		private int size = 30;
		private int xPos = 0;
		private int yPos = 0;
		public Color currentC = Color.BLACK;

		public Squares(int xPosition, int yPosition, Color color){
			this.xPos = xPosition;
			this.yPos = yPosition;
			currentC = color;
		}

		public int getXPos(){
			return xPos;
		}
		public int getYPos(){
			return yPos;
		}
		public Color getColor(){
			return currentC;
		}

		public void setXPos(int xPosition){
			xPos = xPosition;

		}
		public void setYPos(int yPosition){
			yPos = yPosition;
		}
		public void setColor(Color c){
			currentC = c;
		}
		public void draw(int xPosition, int yPosition, Graphics g){
			setXPos(xPosition);
			setYPos(yPosition);
			g.setColor(currentC);
			g.fillRect(xPos, yPos, size, size);
		}
	}
	
	public void populateMaze(int[][] mazeWalls){
		for(int i = 0; i < xWidth; i++){
			for(int j = 0; j < yWidth; j++){
				//mazeWalls[i][j] = 1;
				if(mazeWalls[i][j] == 1){
					maze[i][j] = new Squares(i * 30, j*30, Color.BLUE);
				}
				else{
					maze[i][j] = new Squares(i * 30, j*30, Color.BLACK);
				}
			}
			
		}
		//System.out.println("Size of maze matrix: " + xWidth);

	}

	public void draw(Graphics g){
		for(int i = 0; i < xWidth; i++){
			for(int j = 0; j < yWidth; j++){
				maze[i][j].draw(i * 30, j * 30, g);
			}
		}

	}

}
