import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//solve using two separate lists
public class teque{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int numStr = Integer.parseInt(reader.readLine()); //input number of strings
		PrintWriter writer = new PrintWriter(System.out);
		
		//creating two large arrays to sort all the integers without needing to resize
		int size = numStr * 2;
		ArrDeq frontHalf = new ArrDeq(size);
		ArrDeq backHalf = new ArrDeq(size);
		Integer value = 0;
		
		for (int i = 0; i < numStr; i++) {
			String input = reader.readLine();
			String[] inputSplit = input.split(" ");
			String function = inputSplit[0]; // checking the function input
			value = Integer.parseInt(inputSplit[1]); // storing the input value 
			
			switch (function) {
			case "push_front":
				frontHalf.addFront(value);
				
				//checking to move element from front list to back list to maintain middle
				if (frontHalf.size() > backHalf.size() + 1) {
					Integer removedVal = frontHalf.removeBack();
					backHalf.addFront(removedVal);
				}
				break;
				
			case "push_back":
				backHalf.addBack(value);
				
				// maintaining middle between front and back lists
				if (frontHalf.size() < backHalf.size()) {
					Integer removedVal = backHalf.removeFront();
					frontHalf.addBack(removedVal);
				}
				break;
				
			case "push_middle":
				int totalSize = frontHalf.size() + backHalf.size();
				
				//check if insert to frontHalf or backHalf
				if (totalSize % 2 == 0) {
					frontHalf.addBack(value);
				}
				else {
					backHalf.addFront(value);
				}
				break;
				
			case "get":
				//checking if index in frontHalf or backHalf
				if (value > frontHalf.size() - 1) {
					int index = value - frontHalf.size();
					writer.println(backHalf.valAtIndex(index));
				}
				else {
					writer.println(frontHalf.valAtIndex(value));
				}
				break;
			
			}
		}
		writer.flush();
	}
}
	
class ArrDeq{
	private Integer[] elements;
	private int headIndex;
	private int tailIndex;
	private int size = 0;
	
	public ArrDeq(int totalSize) {
		elements = new Integer[totalSize];
		headIndex = totalSize / 2;
		tailIndex = headIndex;
	}
	
	public void addFront(int value) {
		if (size == 0) {
			elements[headIndex] = value;
			size++;
		}
		else {
			headIndex--;
			elements[headIndex] = value;
			size++;
		}
	}
	
	public void addBack(int value) {
		if (size == 0) {
			elements[tailIndex] = value;
			size++;
		}
		else {
			tailIndex++;
			elements[tailIndex] = value;
			size++;
		}
	}
	
	public Integer removeFront() {
		Integer removedVal = elements[headIndex];
		elements[headIndex] = null;
		size--;
		if (size != 0) headIndex++; //maintain head pointer equal to tail pointer if empty deque
		return removedVal;
	}
	
	public Integer removeBack() {
		Integer removedVal = elements[tailIndex];
		elements[tailIndex] = null;
		size--;
		if (size != 0) tailIndex--; //maintain tail pointer equal to head pointer if empty deque
		return removedVal;
	}
	
	public Integer valAtIndex(int index) {
		return elements[headIndex + index];
	}
	
	public Integer size() {
		return size;
	}
}
