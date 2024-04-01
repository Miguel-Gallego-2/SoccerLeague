
package colombiansoccerleague;


public class Match {
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;

    public Match() {
    }
    
    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }
    
    public void winner(Team team, int goalsScored, int goalsConceded) {
        team.setGoalsScored(goalsScored);
        team.setGoalsConceded(goalsConceded);
        team.setWins(1);
        team.setPoints(3);
    }

    public void losser(Team team, int goalsScored, int goalsConceded) {
        team.setGoalsScored(goalsScored);
        team.setGoalsConceded(goalsConceded);
        team.setLosses(1);
        team.setPoints(0);
    }

    public void draw(Team team1, Team team2, int goals) {
        team1.setGoalsScored(goals);
        team1.setGoalsConceded(goals);
        team1.setPoints(1);
        team1.setDraws(1);
        team2.setGoalsScored(goals);
        team2.setGoalsConceded(goals);
        team2.setPoints(1);
        team2.setDraws(1);
    }
     
}


