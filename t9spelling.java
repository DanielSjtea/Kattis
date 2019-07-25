import java.util.*;

public class t9spelling{
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int tests = sc.nextInt();
		
		// 1 <= N <= 100
		if (tests > 100) tests = 100;
		if (tests < 1) tests = 1;
		
		// String array to hold the words
		String[] allWords = new String[tests + 1];
		// To read the empty line
		String empty = sc.nextLine();
		
		// Reading all the words & creating an array of words
		for (int i = 0; i < tests; i++) {
			String word = sc.nextLine();
			allWords[i] = word;
		}
		
		// Converting all words to values
		for (int i = 1; i <= tests; i++) {
			String current = allWords[i-1];
			String in_num = table(current);
			System.out.println("Case #"+ i + ": "+ in_num);
		}
	}
	
	private static String table(String args) {
		String[] arr = new String[255];
		
		// Hardcode the respective chars to value
		arr['a'] = "2";
		arr['b'] = "22";
		arr['c'] = "222";
		arr['d'] = "3";
		arr['e'] = "33";
		arr['f'] = "333";
		arr['g'] = "4";
		arr['h'] = "44";
		arr['i'] = "444";
		arr['j'] = "5";
		arr['k'] = "55";
		arr['l'] = "555";
		arr['m'] = "6";
		arr['n'] = "66";
		arr['o'] = "666";
		arr['p'] = "7";
		arr['q'] = "77";
		arr['r'] = "777";
		arr['s'] = "7777";
		arr['t'] = "8";
		arr['u'] = "88";
		arr['v'] = "888";
		arr['w'] = "9";
		arr['x'] = "99";
		arr['y'] = "999";
		arr['z'] = "9999";
		arr[' '] = "0";
		String value = "";
		
		// Converting String to Values
		for (int i=0; i < args.length(); i++) {
			char letter = args.charAt(i);
			String to_add = arr[letter];
			
			// Checking if need to put whitespace
			if (value.isEmpty() != true) {
				char prev_last = value.charAt(value.length() - 1);
				char current_first = to_add.charAt(0);
				if (prev_last == current_first) {
					value = value.concat(" ");
				}
			}
			value = value.concat(to_add);
		}
		
		return value;
	}
	
}