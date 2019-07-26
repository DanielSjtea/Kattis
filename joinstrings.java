import java.io.*;

public class joinstrings{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int numStr = Integer.parseInt(reader.readLine()); //input number of strings
		LList[] arrLList = new LList[numStr];
		int a = 0, b = 0;
		
		// reading and storing each string as a LList in index
		for (int i = 0; i < numStr; i++) {
			LList newStrLList = new LList(reader.readLine());
			arrLList[i] = newStrLList;
		}

		for (int i = 0; i < numStr - 1 ; i++) {
			String valuesStr = reader.readLine();
			String[] valuesArr = valuesStr.split(" ");
			a = Integer.parseInt(valuesArr[0]) - 1 ;
			if (valuesArr.length == 2) b = Integer.parseInt(valuesArr[1]) - 1;
			arrLList[a].insert(arrLList[b]);
		}
		
		arrLList[a].printall();
	}	
}

class LList{
	Node head;
	Node tail;
	
	class Node{
		String word;
		LList next;
		
		Node(String input){
			word = input;
			next = null;
		}
		
		public void setNext(LList toAdd) { next = toAdd; }
		
		public LList getNext() { return next; }
		
		public void printWord() { System.out.print(word); }
	}
	
	LList(String input) {
		head = new Node(input);
		tail = head;
	}
	
	public void insert(LList toAdd) {
		tail.setNext(toAdd);
		tail = toAdd.tail;
	}
	
	public void printall() {
		head.printWord();
		LList current = head.getNext();
		while (current != null) {
			current.head.printWord();
			current = current.head.getNext();
		}
	}
}
