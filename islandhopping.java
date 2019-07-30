import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class islandhopping{
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		int cases = Integer.parseInt(reader.readLine());
		
		for (int i = 0; i < cases; i++) {
			int numBridges = Integer.parseInt(reader.readLine());
			//ufds to store parents of each node (to check if a node has been connected)
			UnionFind parentInd = new UnionFind(numBridges);
			
			//storing an EdgeList
			ArrayList<Edge> edgeList = new ArrayList<Edge>();
			double[] allxCoords = new double[numBridges];
			double[] allyCoords = new double[numBridges];
			
			for (int j = 0; j < numBridges; j++) {
				String[] readLine = reader.readLine().split(" ");
				double xCoord = Double.parseDouble(readLine[0]);
				double yCoord = Double.parseDouble(readLine[1]);
				
				allxCoords[j] = xCoord;
				allyCoords[j] = yCoord;
			}
			
			for (int k = 0; k < numBridges; k++) {
				for (int j = k + 1; j < numBridges; j++) {
					double dist = calcShortestDist(allxCoords[k], allyCoords[k], allxCoords[j], allyCoords[j]);
					Edge toAdd = new Edge(k, j, dist);
					edgeList.add(toAdd);
				}
			}
			
			//sort to have shortest edge at front
			edgeList.sort(new SortSmallest());
			
			int counter = 0; // need only numBridges -1 edges
			double totalVal = 0.0;
			int index = 0;
			
			while(counter < numBridges - 1) {
				Edge focusEdge = edgeList.get(index);
				if (!parentInd.isSameSet(focusEdge.fromNode, focusEdge.toNode)) {
					counter++;
					totalVal += focusEdge.dist;
					index++;
					parentInd.unionSet(focusEdge.fromNode, focusEdge.toNode);
				}
				else {
					index++;
				}
			}
			writer.println(totalVal);
		}
		writer.flush();
	}
	
	public static double calcShortestDist(double xCoord1, double yCoord1, double xCoord2, double yCoord2){
		double xDiff = xCoord1 - xCoord2;
		double yDiff = yCoord1 - yCoord2;
		
		return Math.hypot(xDiff, yDiff);
	}
	
	//to check if node has already been visited
	public static boolean checkRoot(int fromNode,int toNode, int[] rootNum) {
		if (rootNum[fromNode] != rootNum[toNode]) {
			return true;
		}
		else
			return false;
	}
}

//Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
class UnionFind {
	public ArrayList<Integer> p, rank, setSize;
	public int numSets;

	public UnionFind(int N) {
		p = new ArrayList<Integer>(N);
		rank = new ArrayList<Integer>(N);
		setSize = new ArrayList<Integer>(N);
		numSets = N;
		for (int i = 0; i < N; i++) {
			p.add(i);
			rank.add(0);
			setSize.add(1);
		}
	}

	public int findSet(int i) { 
		if (p.get(i) == i) return i;
		else {
			int ret = findSet(p.get(i)); p.set(i, ret);
			return ret; 
		} 
	}

	public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

	public void unionSet(int i, int j) { 
		if (!isSameSet(i, j)) { 
			numSets--; 
			int x = findSet(i), y = findSet(j);
			// rank is used to keep the tree short
			if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
			else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
			if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y)+1); } 
		} 
	}

	public int numDisjointSets() { return numSets; }

	public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}

class Edge{
	int fromNode;
	int toNode;
	double dist;
	
	Edge(int fromVert, int toVert, double dist){
		fromNode = fromVert;
		toNode = toVert;
		this.dist = dist;
	}
}

class SortSmallest implements Comparator<Edge>{
	public int compare(Edge a, Edge b) {
		return Double.compare(a.dist, b.dist);
	}
}
