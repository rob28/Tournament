package uk.ac.aber.dcs.rob41.cs21120.assignment1;

import java.util.ArrayList;

import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;

public class SingleElimination implements IManager {
    Queue competitors = new Queue();
    String currentMatchPlayer1;
    String currentMatchPlayer2;
    Boolean Final = false;
    /**
     * Set the players or teams to use in the competition
     * @param players the players or teams
     */
    public void setPlayers(ArrayList<String> players) {
        for (int counter = 0; counter<players.size(); counter++){
            competitors.enQ(players.get(counter));
        }      
    }
   
    /**
     * Return true if there is another match in the competition that can be fetched using nextMatch
     * @return returns true if the competition is still going
     */
    public boolean hasNextMatch() {
        
        if(competitors.length()<=1 ||  Final == true){
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
        currentMatchPlayer1 = competitors.deQ().toString();
        currentMatchPlayer2 = competitors.deQ().toString();
        Match match = new Match(currentMatchPlayer1, currentMatchPlayer2);
        
        return match;
    }
    
    /** Sets the winner for the last retrieved Match
     * 
     * @param player1 should be true if player1 won the match, otherwise false
     */
    public void setMatchWinner(boolean player1) {
        if (competitors.length() == 0){
            competitors.clear();
            Final = true;
        }
        if (player1 == true){
            competitors.enQ(currentMatchPlayer1);
            addRunnerUp(currentMatchPlayer2);
        }else{
            competitors.enQ(currentMatchPlayer2);
            addRunnerUp(currentMatchPlayer1);
        }
        System.out.println(competitors.length());
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
        return (competitors.deQ().toString());
    }
    public void addRunnerUp(String player){
        if(competitors.length()==1){
            competitors.enQ(player);
        }
    }
}


