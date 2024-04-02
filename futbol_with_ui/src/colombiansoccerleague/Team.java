
package colombiansoccerleague;


public class Team {
    private String name;
    private int wins;
    private int losses;
    private int draws;
    private int points;
    private int goalsScored;
    private int goalsConceded;
    private int matchesPlayed;
    public Team() {
    }

    public Team(String name, int wins, int losses, int draws, int points, int goalsScored, int goalsConceded, int matchesPlayed) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.points = points;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded; 
        this.matchesPlayed = matchesPlayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses += losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws += draws;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored += goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded += goalsConceded;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed += matchesPlayed;
    }
}
