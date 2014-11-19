package uk.ac.aber.dcs.rob41.cs21120.assignment1;

import java.util.ArrayList;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.IManager;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.Match;
import uk.ac.aber.dcs.bpt.cs21120.assignment1.NoNextMatchException;


public class BubbleElimination implements IManager{
    Queue unorderedCompetitors = new Queue();
    String [] competitorsPriorityQueue;
    ArrayList<String> orderedCompetitors= new ArrayList<String>();
    int numCompetitors,competitorPosition,parentsPosition;
    String currentMatchPlayer1;
    String currentMatchPlayer2;
    int queueSize,played = 0;
    boolean undefeated,end = false;
    enum Phase {bubbleDown,bubbleUp}
    Phase currentPhase = Phase.bubbleDown;
    enum WhichChild {left,right}
    WhichChild currentChild = WhichChild.left;
    /**
     * Set the players or teams to use in the competition
     * @param players the players or teams
     */
    public void setPlayers(ArrayList<String> players) {
        for (int counter = 0; counter<players.size(); counter++){
            unorderedCompetitors.enQ(players.get(counter));
        }
        numCompetitors = players.size();
        competitorsPriorityQueue = new String [numCompetitors];
    }
    /**
     * Return true if there is another match in the competition that can be fetched using nextMatch
     * @return returns true if the competition is still going
     */
    public boolean hasNextMatch() {
        if(orderedCompetitors.size() == numCompetitors || end == true){
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
        System.out.println(competitorsPriorityQueue[0]+", "+competitorsPriorityQueue[1]+", "+competitorsPriorityQueue[2]+", "+competitorsPriorityQueue[3]+", "+competitorsPriorityQueue[4]+", "+competitorsPriorityQueue[5]+", "+competitorsPriorityQueue[6]+", "+competitorsPriorityQueue[7]+", "+competitorsPriorityQueue[8]);
        //If this is the first run through take 2 competitors
        switch(currentPhase){    
        case bubbleDown:
            if (orderedCompetitors.size()==0 && competitorsPriorityQueue[0]== null){
                currentMatchPlayer1 = unorderedCompetitors.deQ().toString();
                currentMatchPlayer2 = unorderedCompetitors.deQ().toString();
                Match match = new Match(currentMatchPlayer1, currentMatchPlayer2);
                return match;
            //If the inserted team beats their parent and isn't at the front of the queue play their new parent
            }else if(undefeated == true && competitorPosition !=0){
                Match match = new Match((competitorsPriorityQueue[competitorPosition]),(competitorsPriorityQueue[getParent(competitorPosition)]));
                return match;
            //Take the next unordered team and play what will be there parent node (childs position -1 /2)
            }else if (unorderedCompetitors.length() != 0){
                undefeated = false;
                competitorPosition = queueSize;
                //queueSize = competitorsPriorityQueue.length;
                currentMatchPlayer1 = unorderedCompetitors.deQ().toString();
                queueSize++;
                Match match = new Match(currentMatchPlayer1,(competitorsPriorityQueue[getParent(competitorPosition)]));
                return match;    
            }else{
                 currentPhase = Phase.bubbleUp;
                return reIterateBubbleUp();
            }
        case bubbleUp:
            if (getChild(parentsPosition,currentChild)<queueSize){
                Match match = new Match((competitorsPriorityQueue[competitorPosition]),(competitorsPriorityQueue[getChild(parentsPosition,currentChild)]));
                return match;                
            }else if(queueSize == 2){
                Match match = new Match((competitorsPriorityQueue[0]),(competitorsPriorityQueue[1]));
                return match;
            }else{
                return reIterateBubbleUp();
            }
        }
        throw new NoNextMatchException("In neither bubbleDown or bubbleUp");
    }    
    /** Sets the winner for the last retrieved Match
     * 
     * @param player1 should be true if player1 won the match, otherwise false
     */
    public void setMatchWinner(boolean player1) {
        //This is for the first loop 
        if (currentPhase == Phase.bubbleDown){
               if (orderedCompetitors.size() == 0 && queueSize == 0){
                if (player1 == true){
                    competitorsPriorityQueue[queueSize++] = currentMatchPlayer1;//winner
                    competitorsPriorityQueue[queueSize++] = currentMatchPlayer2;//loser
                }else{
                    competitorsPriorityQueue[queueSize++] = currentMatchPlayer2;//winner
                    competitorsPriorityQueue[queueSize++] = currentMatchPlayer1;//loser
                } 
            }else{
                if (player1 == true){
                    parentsPosition = getParent(competitorPosition);
                    String parentLost = competitorsPriorityQueue[parentsPosition];
                    String childWinner = competitorsPriorityQueue[competitorPosition];
                    //depending on if the competitor has just been added to the queue or is bubbling up the queue
                    if (undefeated == true){
                        competitorsPriorityQueue[parentsPosition] = childWinner;                        
                    }else{
                        competitorsPriorityQueue[parentsPosition] = currentMatchPlayer1;
                    }
                    competitorsPriorityQueue[competitorPosition] = parentLost;               
                    competitorPosition = parentsPosition;
                    undefeated = true;
                }else if(undefeated == true){
                    undefeated = false;    
                }else{
                    undefeated = false;
                    competitorsPriorityQueue[competitorPosition] = currentMatchPlayer1;
                }
            }    
        }
        if (currentPhase == Phase.bubbleUp){
            played++;
            boolean correctPlace = false;
            if (queueSize == 2){
                if (player1 == true){
                    addToOrderedCompetitors(0);
                    addToOrderedCompetitors(0);
                }else{
                    addToOrderedCompetitors(1);
                    addToOrderedCompetitors(0);
                }
            }else{
                if (player1 == true){
                    //depending on if the competitor has just been added to the queue or is bubbling up the queue
                    if (undefeated == true && competitorPosition == 0){
                        System.out.println("Instered player won!?");
                        end = true;
                    }else if(undefeated == true && competitorPosition == parentsPosition){
                        correctPlace = true;
                        
                    }
                    undefeated = true;
                    
                //If the parent loses then the child is switched into it's place as the competitor for the next game but not moved in the array
                }else{
                    competitorPosition = getChild(parentsPosition,currentChild);
                    undefeated = false;
                }
                //if there has been two matches in a row then the winner of the second match replaces the inserted player
                if (played == 2){
                    played=0;
                    String insertedLost = competitorsPriorityQueue[parentsPosition];
                    String childWinner = competitorsPriorityQueue[competitorPosition];
                    competitorsPriorityQueue[competitorPosition] = insertedLost;
                    competitorsPriorityQueue[parentsPosition] = childWinner;
                    parentsPosition = competitorPosition;
                    undefeated = false;
                    //If the inserted player beats both it's children this will allow he program to continue
                    if (correctPlace == true){parentsPosition = queueSize;}
                }
                
                
                //switch the child that will be played
                switch (currentChild){
                case left:
                    currentChild = WhichChild.right;
                    break;
                case right:
                    currentChild = WhichChild.left;
                    break;
                }
            }
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
        return (orderedCompetitors.get(n));
    }

    public int getParent(int childsPosition){
        return (childsPosition-1)/2;
    }
    
    private void addToOrderedCompetitors(int n) {
        orderedCompetitors.add(competitorsPriorityQueue[n]);
    } 
    
    private void toFrontCompetitorsPriorityQueue() {
        competitorsPriorityQueue[0]=competitorsPriorityQueue[queueSize-1];
        competitorsPriorityQueue[queueSize-1] = null;
        queueSize--;
    }
    
    private Match reIterateBubbleUp() {
        addToOrderedCompetitors(0);
        toFrontCompetitorsPriorityQueue();
        currentChild = WhichChild.left;
        parentsPosition = 0;
        competitorPosition = 0;    
        played=0;
        System.out.println("Phase.bubbleUp;");
        System.out.println(orderedCompetitors);
        System.out.println(competitorsPriorityQueue[0]+", "+competitorsPriorityQueue[1]+", "+competitorsPriorityQueue[2]+", "+competitorsPriorityQueue[3]+", "+competitorsPriorityQueue[4]+", "+competitorsPriorityQueue[5]+", "+competitorsPriorityQueue[6]+", "+competitorsPriorityQueue[7]+", "+competitorsPriorityQueue[8]);
        Match match = new Match((competitorsPriorityQueue[competitorPosition]),(competitorsPriorityQueue[getChild(parentsPosition,currentChild)]));
        return match;
    }
    
    private int getChild(int parentPos,WhichChild child) {
        int result = 0;
        switch(child){
        case left:
            result =(parentPos*2)+1;
            break;
        case right:
            result =(parentPos*2)+2;
            break;
        }
        return result;
    }
}


