import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class secretchamber{
	private static HashMap<Character, ArrayList<Character>> parentChar = new HashMap<Character, ArrayList<Character>>();
	private static LinkedList<Character> queueBFS;
	private static HashMap<Character, Boolean> visitedBFS;
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] readLine = reader.readLine().split(" ");
		Integer numTrans = Integer.parseInt(readLine[0]);
		Integer numWords = Integer.parseInt(readLine[1]);
		
		//get translations of characters
		for (int i = 0; i < numTrans; i++) {
			char[] letters = reader.readLine().toCharArray();
			char child = letters[0];
			char translated = letters[2];
			if (!parentChar.containsKey(child)) {
				ArrayList<Character> parent = new ArrayList<Character>();
				parent.add(translated);
				parentChar.put(child, parent);
			}
			else {
				ArrayList<Character> toUpdate = parentChar.get(child);
				toUpdate.add(translated);
				parentChar.replace(child, toUpdate);
			}
		}
		
		for (int j = 0; j < numWords; j++) {
			queueBFS = new LinkedList<Character>();
			String[] line = reader.readLine().split(" ");
			char[] initial = line[0].toCharArray();
			char[] end = line[1].toCharArray();
			int length = initial.length;
			
			if (initial.length != end.length) {
				System.out.println("no");
				continue;
			}
			boolean tryTranslate = false;
			for (int k = 0; k < length; k++) {
				queueBFS.clear(); //reset the queue
				char focusInit = initial[k];
				char focusEnd = end[k];
				tryTranslate = BFS(focusInit, focusEnd);
				if (!tryTranslate) {
					System.out.println("no");
					break;
				}
			}
			if(tryTranslate)
				System.out.println("yes");
 		}
	}
	
	public static boolean BFS(char a, char b) {
		visitedBFS = new HashMap<Character, Boolean>();
		if (a == b)
			return true;
		if (!parentChar.containsKey(a)) {
			return false;
		}
		else {
			ArrayList<Character> adjacent = parentChar.get(a);
			visitedBFS.put(a, true);
			for (Character translated : adjacent) {
				queueBFS.add(translated);
			}
			while(!queueBFS.isEmpty()) {
				char focusTranslation = queueBFS.pop();
				// prevent looping
				if (visitedBFS.containsKey(focusTranslation))
					continue;
				if (focusTranslation == b)
					return true;
				visitedBFS.put(focusTranslation, true);
				if (parentChar.containsKey(focusTranslation)) {
					ArrayList<Character> adj = parentChar.get(focusTranslation);
					for (Character trans : adj) {
						queueBFS.add(trans);
					}
				}
			}
			return false;
		}
		
	}
}
