import java.awt.*;

public class pacman extends GameObj {
	
		//public static final String IMG_FILE = "pacman.png";
	    public static final int SIZE = 15;
	    public static final int INIT_POS_X = 0;
	    public static final int INIT_POS_Y = 45;
	    public static final int INIT_VEL_X = 0;
	    public static final int INIT_VEL_Y = 0;
	    //private static BufferedImage img;

	    private Color color;

	    /**
	    * Note that, because we don't need to do anything special when constructing a Square, we simply
	    * use the superclass constructor called with the correct parameters.
	    */
	    public pacman(int courtWidth, int courtHeight, Color color) {
	        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
	    	
	        this.color = color;
	    }

	    @Override
	    public void draw(Graphics g) {
	        g.setColor(this.color);
	        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
	    }
}
