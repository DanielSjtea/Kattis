import java.util.*;
import java.io.*;

public class workstations{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] vals = reader.readLine().split(" ");
		
		//number of researchers
		int numRe = Integer.parseInt(vals[0]);
		// time to autolock
		int lockTime = Integer.parseInt(vals[1]);
		
		//number of workstations needed to be unlocked
		int unlockedNum = 0;
		PriorityQueue<Integer> lockingTimePQ = new PriorityQueue<Integer>();
		
		//storing all researchers arrival and usage time
		ArrayList<Researcher> allRe = new ArrayList<Researcher>();
		
		for (int i = 0; i < numRe; i++) {
			String[] person = reader.readLine().split(" ");
			int arrival = Integer.parseInt(person[0]);
			int useTime = Integer.parseInt(person[1]);
			Researcher newGuy = new Researcher(arrival, useTime);
			allRe.add(newGuy);
		}
		
		//to sort researchers by earliest arrival time
		allRe.sort(new SortByShortest());
		
		for (Researcher person: allRe) {
			int arrival = person.arrivalTime();
			int useTime = person.timeToComplete();
			
			//no unlocked workstations
			if (lockingTimePQ.size() == 0) {
				unlockedNum++;
				//time that new workstation will lock
				int newStationLockTime = arrival + useTime + lockTime;
				lockingTimePQ.add(newStationLockTime);
			}
			
			// have workstation in PQ check if in use or unused and unlocked
			else {
				// assign unused and unlocked workstation
				if (arrival >= lockingTimePQ.peek() - lockTime && arrival <= lockingTimePQ.peek()) {
					lockingTimePQ.poll();
					int newLockTime = arrival + useTime + lockTime;
					lockingTimePQ.add(newLockTime);
				}
				// arrival is before any workstation is unused, unlock new workstation and assign
				else if (arrival < lockingTimePQ.peek() - lockTime) {
					unlockedNum++;
					int newLockTime = arrival + useTime + lockTime;
					lockingTimePQ.add(newLockTime);
				}
				//arrival is AFTER earliest workstation is locked
				else {
					//remove locked workstation
					lockingTimePQ.poll();
					//iterate to check for any unused and unlocked workstation
					while(lockingTimePQ.size() != 0) {
						// if arrival is before earliest unused workstation, break
						if (arrival < lockingTimePQ.peek() - lockTime) {
							break;
						}
						// assign unused and unlocked workstation if arrival time is apt
						else if (arrival >= lockingTimePQ.peek() - lockTime && arrival <= lockingTimePQ.peek()) {
							break;
						}
						else {
							lockingTimePQ.poll();
						}
					}
					
					//unlock a new workstation
					if (lockingTimePQ.size() == 0) {
						unlockedNum++;
						//time that new workstation will lock
						int newStationLockTime = arrival + useTime + lockTime;
						lockingTimePQ.add(newStationLockTime);
					}
					
					else if (arrival >= lockingTimePQ.peek() - lockTime && arrival <= lockingTimePQ.peek()) {
						lockingTimePQ.poll();
						int newStationLockTime = arrival + useTime + lockTime;
						lockingTimePQ.add(newStationLockTime);
					}
					
					// unlock new workstation to assign
					else if (arrival < lockingTimePQ.peek() - lockTime) {
						unlockedNum++;
						//time that new workstation will lock
						int newStationLockTime = arrival + useTime + lockTime;
						lockingTimePQ.add(newStationLockTime);
					}
					
				}
			}
		}
		
		int numSaved = numRe - unlockedNum;
		System.out.println(numSaved);
	}
}

class Researcher{
	int arrival;
	int useTime;
	
	Researcher(int arrival, int useTime){
		this.arrival = arrival;
		this.useTime = useTime;
	}
	
	public int arrivalTime() {
		return arrival;
	}
	
	public int timeToComplete() {
		return useTime;
	}
}

class SortByShortest implements Comparator<Researcher>{
	// sorting researchers in ascending order of arrival
	public int compare(Researcher a, Researcher b) {
		return Integer.compare(a.arrival, b.arrival);
	}
}
