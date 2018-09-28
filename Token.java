import java.awt.Color;
import java.awt.Graphics;


public class Token extends GameObj {
	public static final int SIZE = 0;
	public static final int INIT_POS_X = 0;
	public static final int INIT_POS_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public boolean display = true;

	private Color color;

	    /**
	    * Note that, because we don't need to do anything special when constructing a Square, we simply
	    * use the superclass constructor called with the correct parameters.
	    */
	    public Token(int xpos, int ypos, int size, int courtWidth, int courtHeight, Color color) {
	        super(INIT_VEL_X, INIT_VEL_Y, xpos, ypos, size, size, courtWidth, courtHeight);
	    	
	        this.color = color;
	    }

	    @Override
	    public void draw(Graphics g) {
	        g.setColor(this.color);
	        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
	    }

}
