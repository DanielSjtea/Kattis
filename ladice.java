import java.io.*;
import java.util.*;

public class ladice{
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] toRead = reader.readLine().split(" ");
		PrintWriter writer = new PrintWriter(System.out);
		
		int numItems = Integer.parseInt(toRead[0]);
		int numDrawers = Integer.parseInt(toRead[1]);
		
		UFDS allDrawers = new UFDS(numDrawers);
		
		for (int i = 0; i < numItems; i++) {
			String[] currLine = reader.readLine().split(" ");
			int drawer1 = Integer.parseInt(currLine[0]);
			int drawer2 = Integer.parseInt(currLine[1]);
			
			if (allDrawers.isSameSet(drawer1, drawer2)) {
				//check if item can be inserted
				boolean checker = allDrawers.insert(drawer1);
				if (checker) 
					writer.println("LADICA");
				else
					writer.println("SMECE");
			}
			else {
				allDrawers.unionSet(drawer1, drawer2);
				boolean checker = allDrawers.insert(drawer1);
				if (checker) 
					writer.println("LADICA");
				else
					writer.println("SMECE");
			}
		}
		
		writer.flush();
	}
}


// following the UFDS code given
class UFDS{
	public ArrayList<Integer> p, rank, setEmpty;
	
	public UFDS(int N) {
		//using 1 indexing
		p = new ArrayList<Integer>(N+1);
		rank = new ArrayList<Integer>(N+1);
		//storing the number of empty spaces left in a drawer set (updated value stored at set parent)
		setEmpty = new ArrayList<Integer>(N+1); 
		for (int i = 0; i < N+1; i++) {
		      p.add(i);
		      rank.add(0);
		      setEmpty.add(1);
		}
	}
	
	public int findSet(int i) { 
	    if (p.get(i) == i) return i;
	    else {
	      int ret = findSet(p.get(i)); 
	      p.set(i, ret);
	      return ret; } 
	  }
	
	public boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}
	
	public void unionSet(int i, int j) {
		if(!isSameSet(i, j)) {
			int x = findSet(i), y = findSet(j);
			int totalEmpty = setEmpty.get(y) + setEmpty.get(x);
			if (rank.get(x) > rank.get(y)) {
				p.set(y, x);
				setEmpty.set(x, totalEmpty);
			}
			else {
				p.set(x,y);
				setEmpty.set(y, totalEmpty);
				if (rank.get(x) == rank.get(y))
					rank.set(y, rank.get(y)+1);
			}
		}
	}
	
	// additional method to check if item can be inserted into drawer
	public boolean insert(int i) {
		int x = findSet(i);
		if (setEmpty.get(x) != 0) {
			setEmpty.set(x, setEmpty.get(x) - 1);
			return true; 
		}
		else {
			return false;
		}
	}
}
