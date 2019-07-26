import java.util.*;

public class bestrelayteam{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numRunners = sc.nextInt();
		sc.nextLine(); // dummy read to remove blank line
		Runner[] allRunners = new Runner[numRunners];
				
		// to get input of all runners
		for (int i = 0; i < numRunners; i++) {
			 String [] person = sc.nextLine().split(" ");
			 String name = person[0];
			 double lap1 = Double.parseDouble(person[1]);
			 double lap2 = Double.parseDouble(person[2]);
			 Runner tempPerson = new Runner(name, lap1, lap2);
			 allRunners[i] = tempPerson;
		}
		
		Arrays.sort(allRunners, new SortbyLap2());
		
		fastest(allRunners, numRunners);
		
	}
	
	private static void fastest(Runner[] args, int numRunners) {
		double totalTiming = 0.0;
		String lap1Runner = "";
		String[] lap2Runners = new String[3];
		double topThreeTimes = 0.0;
		String[] topThreeNames = new String[3];
		int checker = 0; //to check which names to output
		
		for (int i = 0; i < 3; i++) {
			topThreeTimes += args[i].getLap2();
			topThreeNames[i] = args[i].getName();
		}
		
		for (int i = 0; i < numRunners; i++) {
			if (i == 0) {
				totalTiming += args[i].getLap1();
				lap1Runner = args[i].getName();
				for (int j = 1; j <= 3; j++) {
					totalTiming += args[j].getLap2();
					lap2Runners[j-1] = args[j].getName();
				}
				checker = 0;
			}
			else {
				// calculating timing with new first lap runner (top three second lap runners are constant)
				if (i > 3) {
					double tempTime = args[i].getLap1() + topThreeTimes;
					if (Double.compare(tempTime, totalTiming) < 0) {
						totalTiming = tempTime;
						lap1Runner = args[i].getName();
						checker = 1;
					}
				}
				else {
					int counter = 0, j = 0;
					double tempTime = args[i].getLap1();
					String[] tempNames = new String[3];
					
					// calculating timing with new first lap runner
					while (counter < 3) {
						// exclude the first runner (in the top 3 second lap runners)
						if (i == j) {
							j++;
							continue;
						}
						tempTime += args[j].getLap2();
						tempNames[counter] = args[j].getName();
						j++;
						counter++;
						}
					// check if current timing is faster & update if true
					if (Double.compare(tempTime, totalTiming) < 0) {
						totalTiming = tempTime;
						for (int k = 0; k < 3; k++) lap2Runners[k] = tempNames[k];
						lap1Runner = args[i].getName();
						checker = 0;
					}
				}
			}
		}
		
		System.out.println(totalTiming);
		System.out.println(lap1Runner);
		if (checker == 0) {
			for (String name: lap2Runners) {
				System.out.println(name);
			}
		}
		else if (checker == 1) {
			for (String name: topThreeNames) {
				System.out.println(name);
			}
		}
	}
	
}

class Runner{
	String name;
	double lap1, lap2;
	
	public Runner(String iName, double iLap1, double iLap2) {
		setName(iName);
		setLap1(iLap1);
		setLap2(iLap2);
	}
	
	public String setName(String iName) { return name = iName; }
	public double setLap1(double iLap1) { return lap1 = iLap1; }
	public double setLap2(double iLap2) { return lap2 = iLap2; }
	public String getName() { return name; }
	public double getLap1() { return lap1; }
	public double getLap2() { return lap2; }
}

class SortbyLap2 implements Comparator<Runner>{
	// to sort lap2 in ascending order
	public int compare(Runner a, Runner b)
	{
		return Double.compare(a.getLap2(), b.getLap2());
	}
}
