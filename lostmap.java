import java.io.*;
import java.util.*;

public class lostmap{
	private static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(reader.readLine());
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		int[] vertexVals = new int[number+1];
		
		matrix = new int[number+1][number+1];
		UnionFind parentInd = new UnionFind((number*number));
		
		for (int i = 1; i < number; i++) {
			String[] readLine = reader.readLine().split(" ");
			for (int j = i; j < number; j++) {
				
				Edge toAdd = new Edge(i, j+1, Integer.parseInt(readLine[j]));
				edgeList.add(toAdd);
			}
		}
		
		edgeList.sort(new SortSmallest());
		
		int counter = 0;
		int index = 0;
		
		while (counter < number - 1) {
			Edge focusEdge = edgeList.get(index);
			if (!parentInd.isSameSet(focusEdge.fromNode, focusEdge.toNode)) {
				counter++;
				index++;
				parentInd.unionSet(focusEdge.fromNode, focusEdge.toNode);
				System.out.println(focusEdge.fromNode + " " + focusEdge.toNode);
			}
			else {
				index++;
			}
		}
		
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
	int dist;
	
	Edge(int fromVert, int toVert, int dist){
		fromNode = fromVert;
		toNode = toVert;
		this.dist = dist;
	}
}

class SortSmallest implements Comparator<Edge>{
	public int compare(Edge a, Edge b) {
		return Integer.compare(a.dist, b.dist);
	}
}