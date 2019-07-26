import java.util.*;

public class sortofsorting{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			int n = sc.nextInt();
			if (n == 0) break; // check if final
			
			String dummy = sc.nextLine(); // to clear whitespace
			String[] names = new String[n]; // store all names
			
			for (int i = 0; i < n; i++) {
				names[i] = sc.nextLine();
			}
			
			Arrays.sort(names, new StrCompare());
			
			for (int i = 0; i < n; i++) {
				System.out.println(names[i]);
			}
			
			System.out.println(); //to make a blank space between sets of names
		}
	}
	
	static class StrCompare implements Comparator<String>{
		
		public int compare(String name1, String name2) {
			String subname1 = name1.substring(0, 2);
			String subname2 = name2.substring(0, 2);
			return subname1.compareTo(subname2);
		}
	}
	
}
