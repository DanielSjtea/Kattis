import java.io.*;

public class trainpassengers{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] totalCap_numStations = reader.readLine().split(" ");
		int totalCap = Integer.parseInt(totalCap_numStations[0]);
		int numStations = Integer.parseInt(totalCap_numStations[1]);
		int currCap = 0;
		Boolean possible = false;
		
		
		for (int i = 0; i < numStations; i++) {
			String[] currLine = reader.readLine().split(" ");
			int left = Integer.parseInt(currLine[0]);
			int entered = Integer.parseInt(currLine[1]);
			int stayStation = Integer.parseInt(currLine[2]);
			currCap -= left;
			if (currCap < 0) break;
			currCap += entered;
			if (currCap > totalCap) break;
			if (stayStation != 0 && currCap < totalCap) {
				currCap += stayStation;
				break;
			}
		}
		possible = (currCap == 0);
		
		if(possible) System.out.println("possible");
		else System.out.println("impossible");
	}
}

