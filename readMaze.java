import java.io.BufferedReader;
import java.io.FileReader;

public class readMaze {
	static int [][] mazeMatrix  = new int[10][10];
	
	public static int[][] readFile(String filename){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int row = 0;
			
			
			while(br.ready()){
				String line = br.readLine();
				line = line.trim();
				String[] nums = line.split("\\s+");
				for(int i = 0; i < nums.length; i++){
					mazeMatrix[i][row] = Integer.parseInt(nums[i]);
				}
				row++;
				
			}

		} catch (Exception e) {
			System.out.println("Trouble reading in maze");
			
			//e.printStackTrace();
		}

		return mazeMatrix;
	}
	

}
