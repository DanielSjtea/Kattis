/* 
 *  Name: Daniel Seah Jieh Tzen
 *  Matric No: A0180335L
 *  Collaborators: Nil
*/

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class amazing{

	private static String[] directions = {"down", "right", "up", "left"};
	private static Integer[] shiftX = {0, 1, 0, -1};
	private static Integer[] shiftY = {-1, 0, 1, 0};
	private static HashMap<Coords, Boolean> dfs_visited = new HashMap<Coords, Boolean>();
	private static Stack<Cell> traversedCells = new Stack<Cell>(); 
	private static int initX = 0;
	private static int initY = 0;
	private static boolean solved = false;
	private static boolean wrong = false;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] arg) throws IOException {
		dfs(initX, initY);
		//no solution found & not wrong
		if (solved == false && wrong == false) {
			writer.write("no way out" + "\n");
			writer.flush();
			//System.out.println("no way out");
			//System.out.flush();
		}
		//read the solved reply
		String last = reader.readLine();
	}
	
	public static void dfs(int xCoord,int yCoord) throws IOException {
		Coords focusCoords = new Coords(xCoord, yCoord);
		//set current coordinates to visited if not previously done
		if (!dfs_visited.containsKey(focusCoords)) {
			dfs_visited.put(focusCoords, true);
		}
		
		// for the starting coordinate, check all 4 directions
		if (traversedCells.isEmpty()) {
			for (int i = 0; i < 4; i++) {
				//end the iteration if end has been found or wrong
				if (solved || wrong)
					break;
				
				int nextXCoord = xCoord + shiftX[i];
				int nextYCoord = yCoord + shiftY[i];
				Coords nextCoords = new Coords(nextXCoord, nextYCoord);
				//if this direction of coordinates has previously been visited, skip it
				if (dfs_visited.containsKey(nextCoords)) {
					if (dfs_visited.get(nextCoords))
						continue;
				}
				
				writer.write(directions[i] + "\n");
				writer.flush();
				//System.out.println(directions[i]);
				
				String judgeResp = reader.readLine();
				if (judgeResp.equals("wall")) {
					continue;
				}
				if (judgeResp.equals("ok")) {
					int direction = i;
					Cell currCell = new Cell(nextXCoord, nextYCoord, direction);
					traversedCells.push(currCell);
					dfs(nextXCoord, nextYCoord);
				}
				if (judgeResp.equals("solved")) {
					solved = true;
					break;
				}
				if (judgeResp.equals("wrong")) {
					wrong = true;
					writer.write("no way out" + "\n");
					writer.flush();
					//System.out.println("no way out");
					//System.out.flush();
					break;
				}
			}
		}
		//traversedCells is NOT empty, hence indicates a previous cell => be careful of backtracking unintentionally
		else {
			for (int i = 0; i < 5; i++) {
				//end the iteration if end has been found or wrong
				if(solved || wrong) {
					break;
				}
				
				//if i == 4, all other directions have been checked, backtrack & end this loop
				if (i == 4) {
					Cell prevCell = traversedCells.pop();
					int reverseInd = prevCell.reverseInd;
					//System.out.println(directions[reverseInd]);
					//System.out.flush();
					writer.write(directions[reverseInd] + "\n");
					writer.flush();
					
					String throwaway = reader.readLine();
					return;
				}
				
				int nextXCoord = xCoord + shiftX[i];
				int nextYCoord = yCoord + shiftY[i];
				Coords nextCoords = new Coords(nextXCoord, nextYCoord);
				//if this direction of coordinates has previously been visited, skip it
				if (dfs_visited.containsKey(nextCoords)) {
					if (dfs_visited.get(nextCoords))
						continue;
				}
			
				writer.write(directions[i] + "\n");
				writer.flush();
				//System.out.println(directions[i]);
				//System.out.flush();
				
				String judgeResp = reader.readLine();
				if (judgeResp.equals("wall")) {
					continue;
				}
				//entering new coord, add currCell to traversedCells stack & start dfs in new coord
				if (judgeResp.equals("ok")) {
					int direction = i;
					Cell currCell = new Cell(nextXCoord, nextYCoord, direction);
					traversedCells.push(currCell);
					dfs(nextXCoord, nextYCoord);
				}
				if (judgeResp.equals("solved")) {
					solved = true;
					return;
				}
				if (judgeResp.equals("wrong")) {
					wrong = true;
					writer.write("no way out" + "\n");
					writer.flush();
					//System.out.println("no way out");
					//System.out.flush();
					return;
				}
			}
		}
	}
}

class Coords{
	int xVal, yVal;
	Coords(int x, int y){
		xVal = x;
		yVal = y;
	}
	
	@Override
	public boolean equals(Object a) {
		if (this == a)
			return true;
		if (a == null || getClass() != a.getClass())
			return false;
		Coords in = (Coords) a;
		if (xVal != in.xVal || yVal != in.yVal)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(xVal , yVal);
	}
}

class Cell{
	int xVal, yVal, directionInd, reverseInd;
	
	Cell(int x, int y, int dir){
		xVal = x;
		yVal = y;
		directionInd = dir;
		reverseInd = dir + 2; //to find reverse direction for backtracking
		if (reverseInd >= 4)
			reverseInd -= 4;
	}
}