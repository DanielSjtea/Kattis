import java.util.*;

public class pairingsocks{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long pairs = sc.nextLong();
		long count = 0;
		long completedPairs = 0;
		
		Stack<Long> sockStack1 = new Stack<Long>();
		Stack<Long> sockStack2 = new Stack<Long>();
		
		//reversing the order of socks input, such that the first input ends up on the top of sockStack1
		for (long i = 0; i < pairs * 2; i++ ) {
			sockStack2.push(sc.nextLong());
		}
		for (long i = 0; i < pairs * 2; i++ ) {
			long sockOut = sockStack2.pop();
			sockStack1.push(sockOut);
		}
		
		//first step of moving 1 sock over to auxiliary stack
		long transfSock = sockStack1.pop();
		sockStack2.push(transfSock);
		count++;
		
		while (sockStack1.isEmpty() != true) {
			// condition of matching socks and remove
			if (sockStack2.isEmpty() != true && sockStack1.peek().equals(sockStack2.peek())) {
				sockStack1.pop();
				sockStack2.pop();
				count++;
				completedPairs++;
				}
				
			// moving sock from stack1 to stack2 if cannot match socks
			else {
				long sockOut = sockStack1.pop();
				sockStack2.push(sockOut);
				count++;
				}
		}
		
		if (pairs == completedPairs) System.out.println(count);
		else System.out.println("impossible");
	}
}