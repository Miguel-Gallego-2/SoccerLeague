
package colombiansoccerleague;

import java.util.ArrayList;

public class MatchDate {
   private ArrayList<Match> matches;
   private int matchDateNumber;
   
    public MatchDate() {
        
    }
    
    public MatchDate(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public int getMatchDateNumber() {
        return matchDateNumber;
    }

    public void setMatchDateNumber(int matchDateNumber) {
        this.matchDateNumber = matchDateNumber;
    }
    
    
}
