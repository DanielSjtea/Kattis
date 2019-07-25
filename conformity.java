import java.util.*;

public class conformity{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//no of frosh
		int num = sc.nextInt();
		sc.nextLine(); //dummy read
		
		HashMap<String, Integer> coursesHash = new HashMap<String, Integer>();
		
		for (int i = 0; i < num; i++) {
			String strCourses = sc.nextLine();
			String[] arrayCourses = strCourses.split(" ");
			Arrays.sort(arrayCourses);
			String keyCourses = "";
			for (String course : arrayCourses) {
				keyCourses = keyCourses.concat(course);
			}

			// check if combination of courses already in coursesHash
			if (coursesHash.containsKey(keyCourses)) {
				int val = coursesHash.get(keyCourses);
				coursesHash.replace(keyCourses, ++val);
			}
			// else add new combination to coursesHash
			else {
				coursesHash.put(keyCourses, 1);
			}
		}
		
		Set<String> allKeys = coursesHash.keySet();
		int maxVal = 0;
		int count = 0;
		for (String key : allKeys) {
			//the number of students with the courses combination of "key"
			int numCombi = coursesHash.get(key);
			// if number of max combinations is more than others, count = num of students taking
			if (numCombi > maxVal){
				maxVal = numCombi;
				count = numCombi;
				
			}
			// if number of max combinations is the same, increment count by num of students taking
			else if (numCombi == maxVal) count += coursesHash.get(key);
		}
		
		System.out.println(count);
	}
}