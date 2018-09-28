

import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Instruction extends JFrame implements Runnable {
	TreeMap<Integer, Double> scores;
	public Instruction(TreeMap<Integer, Double> highscores){
		
		scores = highscores;
	}
    	private final JFrame frame2 = new JFrame("HOME PAGE");
    	public void run() {
    		frame2.setLocation(100, 100);
    		frame2.setSize(400, 400);
    		
    		
    		//label1.setHorizontalAlignment(WIDTH/2);
    		//label1.setVerticalAlignment(JLabel.TOP);
    		String instructions = "<html>Welcome to PacMan 2.0. The object of the game is to"
    				+ " use the keys on the keypad to move the pacman(yellow circle) around the"
    				+ " screen, collecting tokens. Beware of the ghosts (white circles), as they can "
    				+ "go through"
    				+ " anyting. See how many levels you can get through as each level, most ghosts "
    				+ "are added and they increase speed. GAME ON!" + "<br><br>" + "HIGH SCORES: ";
    		String allScores = "";
    		
    		for(Entry<Integer, Double> entrySet : scores.entrySet()){
    			int key = entrySet.getKey();
    			double value = entrySet.getValue();
    			allScores = "<br> Score: " + key + " Time: " + value + allScores;
    		}

    		JLabel label2 = new JLabel((instructions + allScores)  + " <html>");
    		
    		//frame2.add(label1);
    		frame2.add(label2);
    		//frame2.pack();
    		frame2.setDefaultCloseOperation(HIDE_ON_CLOSE);
            //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setVisible(true);

    	}
}
