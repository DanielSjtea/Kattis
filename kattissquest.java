import java.io.*;
import java.util.*;

public class kattissquest{
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int numCommands = Integer.parseInt(reader.readLine());
		TreeSet<Quest> allQuests = new TreeSet<Quest>(new SortLargestEnergy());
		
		for (int i = 0; i < numCommands; i++) {
			String[] lineRead = reader.readLine().split(" ");
			String command = lineRead[0];
			
			// adding a new Quest into TreeSet allQuests
			if (command.equals("add")) {
				int energyCost = Integer.parseInt(lineRead[1]);
				int goldReward = Integer.parseInt(lineRead[2]);
				Quest addQuest = new Quest(energyCost, goldReward, i);
				allQuests.add(addQuest);
			}
			
			
			else if (command.equals("query")) {
				int remainingEnergy = Integer.parseInt(lineRead[1]);
				long totalGoldEarn = 0;
				
				// iterating to find all quests to complete
				while (remainingEnergy > 0) {
					Quest energyLeft = new Quest(remainingEnergy, Integer.MAX_VALUE, 0);
					Quest questToClear = allQuests.floor(energyLeft);
					if (questToClear == null)
						break;
					
					remainingEnergy -= questToClear.getEnergy();
					totalGoldEarn += questToClear.getGold();
					allQuests.remove(questToClear);
				}
				
				System.out.println(totalGoldEarn);
			}
		}
	}
}

class Quest{
	private int energyCost;
	private int goldReward;
	private long uniqueID; // a unique identifier to ensure quests with same 
	
	Quest(int energyCost, int goldReward, int i){
		this.energyCost = energyCost;
		this.goldReward = goldReward;
		this.uniqueID = energyCost * 7 + goldReward * 13 + i * 17;
	}
	
	public int getEnergy() {
		return energyCost;
	}
	
	public int getGold() {
		return goldReward;
	}
	
	public long getID() {
		return uniqueID;
	}
	
	public boolean equals(Quest q) {
		if (uniqueID == q.uniqueID)
			return true;
		else
			return false;
	}
}

class SortLargestEnergy implements Comparator<Quest>{
	// sorting quests by energy cost, from max
	public int compare(Quest a, Quest b) {
		Integer diffEnergy = Integer.compare(a.getEnergy(), b.getEnergy());
		Integer diffGold = Integer.compare(a.getGold(), b.getGold());
		Integer diffID = Long.compare(a.getID(), b.getID());
		
		//to sort by gold if same energy, and if same energy and gold, add item without deleting duplicate 
		if (diffEnergy != 0) {
			return diffEnergy;
		}
		else if (diffGold != 0) {
			return diffGold;
		}
		else {
			return diffID;
		}
	}
}

