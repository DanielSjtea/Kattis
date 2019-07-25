import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class weakvertices{
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		
		while (true) {
			int numVerts = Integer.parseInt(reader.readLine());
			if (numVerts == -1) {
				break;
			}
			else if (numVerts == 1) {
				int readVal = Integer.parseInt(reader.readLine());
				writer.println(0);
			}
			else if (numVerts == 2) {
				//two dummy reads to remove lines
				reader.readLine();
				reader.readLine();
				writer.println(0 + " " + 1);
			}
			else {
				ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
				for (int i = 0; i < numVerts; i++) {
					adjList.add(new ArrayList<Integer> ());
					
					String[] singleAdj = reader.readLine().split(" ");
					for (int j = 0; j < singleAdj.length ; j++) {
						int val = Integer.parseInt(singleAdj[j]);
						adjList.get(i).add(val);
					}
				}
				
				// to store all the answers
				ArrayList<Integer> weakVertList = new ArrayList<Integer>();
				
				for (int k = 0; k < numVerts; k++) {
					boolean isWeak = true;
					for (int m = 0; m < numVerts; m++) {
						//if focusVal == 1 means that node k is connected to node m
						int focusVal = adjList.get(k).get(m);
						if (focusVal == 1) {
							//check down the column for matching edge
							for (int rowInd = 0; rowInd < numVerts; rowInd++ ) {
								int focusRowVal = adjList.get(rowInd).get(m);
								if (focusRowVal == 1) {
									int lastRowVal = adjList.get(rowInd).get(k);
									if (lastRowVal == 1) {
										isWeak = false;
									}
								}
							}
						}
						if (isWeak == false) {
							break;
						}
					}
					if (isWeak) {
						weakVertList.add(k);
					}
				}
				
				if (!weakVertList.isEmpty()) {
					writer.print(weakVertList.get(0));
					for (int ind = 1; ind < weakVertList.size(); ind++ ) {
						writer.print(" " + weakVertList.get(ind));
					}
					writer.println();
				}
			}
		}
		writer.flush();
	}
}