import java.io.*;
import java.util.*;

public class islands3{
	public static char[][] gridVals;
	public static boolean[][] dfs_visited;
	public static int minIslands = 0;
	public static int[] shiftCol = {1, -1, 0, 0}; //X axis
	public static int[] shiftRow = {0, 0, 1, -1}; //Y axis
	public static int numRows;
	public static int numCols;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] readLine = reader.readLine().split(" ");
		numRows = Integer.parseInt(readLine[0]);
		numCols = Integer.parseInt(readLine[1]);
		
		gridVals = new char[numRows][numCols];
		dfs_visited = new boolean[numRows][numCols];
		
		for (int i = 0; i < numRows; i++) {
			String currLine = reader.readLine();
			char[] charLine = currLine.toCharArray();
			gridVals[i] = charLine;
		}
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				//check for island only if not previously visited and grid is a land
				if(!dfs_visited[row][col] && gridVals[row][col] == 'L') {
					dfs(row, col);
					minIslands++;
				}
			}
		}
		
		System.out.println(minIslands);
	}
	
	public static void dfs(int row, int col) {
		//check again if previously visited this grid value, stop if reach a grid value that has already been visited
		if (dfs_visited[row][col])
			return;
		//mark current grid value as visited
		dfs_visited[row][col] = true;
		//check all directions
		for (int i = 0; i < 4; i++) {
			//shift the coordinates according to direction
			//index 0 => right, 1 => left, 2 => down, 3 => up
			int nextRow = row + shiftRow[i];
			int nextCol = col + shiftCol[i];
			//all the out of bounds conditions, skip
			if (nextRow < 0)
				continue;
			if (nextCol < 0)
				continue;
			if (nextRow >= numRows)
				continue;
			if (nextCol >= numCols)
				continue;
			
			//check if next grid can be traversed (occurs if next grid location is either 'C' or 'L')
			if (gridVals[nextRow][nextCol] == 'C' || gridVals[nextRow][nextCol] == 'L') {
				dfs(nextRow, nextCol);
			}
		}
	}
}
