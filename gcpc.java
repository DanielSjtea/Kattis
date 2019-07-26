import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class gcpc{
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		String[] lineRead = reader.readLine().split(" ");
		int numTeam = Integer.parseInt(lineRead[0]);
		int numEvent = Integer.parseInt(lineRead[1]);
		
		TreeSet<TeamNode> winningSet = new TreeSet<TeamNode>(new SortSmallestWin());
		TeamNode[] allTeams = new TeamNode[numTeam + 1];
		
		for(int i = 0; i < numEvent; i++) {
			String[] lineRead2 = reader.readLine().split(" ");
			int teamNo = Integer.parseInt(lineRead2[0]);
			int penaltyNo = Integer.parseInt(lineRead2[1]);
			
			if(teamNo == 1) {
				if (allTeams[1] == null) {
					TeamNode myTeam = new TeamNode(1, penaltyNo, 1);
					allTeams[1] = myTeam;
					//removing all teams that myTeam now wins
					while (!winningSet.isEmpty()) {
						if (wins(myTeam, winningSet.first())) {
							winningSet.pollFirst(); //removing top value since myTeam wins it
						}
						else
							break;
					}
					int position = winningSet.size() + 1;
					//System.out.println(position);
					writer.println(position);
				}
				else {
					TeamNode prevVals = allTeams[1];

					int newScore = prevVals.score + 1;
					int newPenalty = prevVals.penalty + penaltyNo;
					TeamNode newMyTeam = new TeamNode(newScore, newPenalty, teamNo);
					allTeams[1] = newMyTeam;
					
					//removing all teams that myTeam now wins
					while (!winningSet.isEmpty()) {
						if (wins(newMyTeam, winningSet.first())) {
							winningSet.pollFirst(); //removing top value since myTeam wins it
						}
						else
							break;
					}
					int position = winningSet.size() + 1;
					//System.out.println(position);
					writer.println(position);
				}
				
				
			}
			else {
				//first time this team scored
				if (allTeams[teamNo] == null) {
					TeamNode addTeam = new TeamNode(1, penaltyNo, teamNo);
					allTeams[teamNo] = addTeam;
					
					//if 1st team hasn't scored yet, add team to winningSet
					if (allTeams[1] == null) {
						winningSet.add(addTeam);
						int position = winningSet.size() + 1;
						//System.out.println(position);
						writer.println(position);
					}
					//else check if this team wins 1st team
					else {
						if (wins(addTeam, allTeams[1])) {
							winningSet.add(addTeam);
							int position = winningSet.size() + 1;
							//System.out.println(position);
							writer.println(position);
						}
						else {
							int position = winningSet.size() + 1;
							//System.out.println(position);
							writer.println(position);
 						}
					}
				}
				else {
					TeamNode prevVals = allTeams[teamNo];
					winningSet.remove(prevVals);
					
					int newScore = prevVals.score + 1;
					int newPenalty = prevVals.penalty + penaltyNo;
					TeamNode addTeam = new TeamNode(newScore, newPenalty, teamNo);
					allTeams[teamNo] = addTeam;
					
					//if 1st team hasn't scored yet, add team to winningSet
					if (allTeams[1] == null) {
						winningSet.add(addTeam);
						int position = winningSet.size() + 1;
						//System.out.println(position);
						writer.println(position);
					}
					//else check if this team wins 1st team
					else {
						if (wins(addTeam, allTeams[1])) {
							winningSet.add(addTeam);
							int position = winningSet.size() + 1;
							//System.out.println(position);
							writer.println(position);
						}
						else {
							int position = winningSet.size() + 1;
							//System.out.println(position);
							writer.println(position);
						}
					}
				}
			}
		}
		writer.flush();
	}
	
	//returns true only if TeamNode a wins TeamNode b
	public static boolean wins(TeamNode a, TeamNode b) {
		if (a.score != b.score) {
			if (a.score > b.score)
				return true;
			else
				return false;
		}
		else if (a.penalty != b.penalty) {
			if (a.penalty < b.penalty) {
				return true;
			}
			else
				return false;
		}
		else
			return a.teamNum < b.teamNum;
	}
}

class TeamNode{
	int score, penalty, teamNum, UniqueID;
	
	TeamNode(int score,int penalty,int teamNum){
		this.score = score;
		this.penalty = penalty;
		this.teamNum = teamNum;
		UniqueID = score * 7 + penalty * 11 + teamNum * 13;
	}
}

class SortSmallestWin implements Comparator<TeamNode>{
	public int compare(TeamNode a, TeamNode b) {
		int diffScore = Integer.compare(a.score, b.score);
		int diffPenalty = Integer.compare(b.penalty, a.penalty);
		int diffUniqueID = Integer.compare(a.UniqueID, b.UniqueID);
		if (diffScore != 0)
			return diffScore;
		else if (diffPenalty != 0)
			return diffPenalty;
		else
			return diffUniqueID;
	}
}
