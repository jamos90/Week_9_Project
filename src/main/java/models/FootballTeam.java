package models;

public class FootballTeam extends Team {
    private int goalsScored;
    private int goalsConceded;

    public FootballTeam(String name, Manager manager, League league, int points, String teamLogo, int goalsScored, int goalsConceded){
        super(name, manager,league,points,teamLogo);
        this.goalsScored = goalsScored;
        this.goalsConceded =goalsConceded;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }
}
