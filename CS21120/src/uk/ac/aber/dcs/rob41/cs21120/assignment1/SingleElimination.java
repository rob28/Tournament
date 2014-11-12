package uk.ac.aber.dcs.rob41.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class SingleElimination implements IManager {
	ArrayList<String> competitors;
	Boolean Final = false;
	/**
     * Set the players or teams to use in the competition
     * @param players the players or teams
     */
    public void setPlayers(ArrayList<String> players) {
    	competitors = new ArrayList<String> (players);
	}
   
    /**
     * Return true if there is another match in the competition that can be fetched using nextMatch
     * @return returns true if the competition is still going
     */
    public boolean hasNextMatch() {
    	
    	if(competitors.size()<=1 ||  Final == true){
    		return false;	
    	}else{
    		return true;
    	} 		
	}
    
    /**
     * Returns the nextMatch to play
     * @return returns the next match
     * @throws NoNextMatchException if the competition is over and no more matches
     */
    public Match nextMatch() throws NoNextMatchException {
    	Match match = new Match((competitors.get(0)),(competitors.get(1)));
    	
		return match;
	}
    
    /** Sets the winner for the last retrieved Match
     * 
     * @param player1 should be true if player1 won the match, otherwise false
     */
    public void setMatchWinner(boolean player1) {
    	if (competitors.size() == 2){
    		Final = true;
	    	 if (player1 == true){
	    		String winner = competitors.get(0);
	    		String runnerUp = competitors.get(1);
	    		competitors.add(winner);
	    		competitors.add(runnerUp);
	    		competitors.remove(1);
	    		competitors.remove(0);
	    	}else{
	    		String winner = competitors.get(1);
	    		String runnerUp = competitors.get(0);
	    		competitors.add(winner);
	    		competitors.add(runnerUp);
	    		competitors.remove(1);
	    		competitors.remove(0);
	    	}
    	}else if (player1 == true){
    		String winner = competitors.get(0);
    		competitors.add(winner);
    		competitors.remove(1);
    		competitors.remove(0);
    	}else{
    		String winner = competitors.get(1);
    		competitors.add(winner);
    		competitors.remove(1);
    		competitors.remove(0);
    	}
	}
    
    /** 
     * Get the name of the player/team that finished in position n.  
     * The returned value should be null if the competition is still running, or if the competition hasn't
     * determined who came in place n.  e.g. a single elimination competition can only (validly) return the
     * winner (n=0).
     * @param n the position to return
     * @return returns the name of the team/player, or null if competition still running or n too large
     */
    public String getPosition(int n) {
		return (competitors.get(n));
	}
}
