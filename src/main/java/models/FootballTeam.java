package models;

public class FootballTeam extends Team {
    private int goalsScored;
    private int goalsConceded;


    public FootballTeam(String name, Manager manager, League league, int points, String teamLogo, int goalsScored, int goalsConceded, String location){
        super(name, manager,league,points,teamLogo,location);
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
    }

    public FootballTeam(){};

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

    public int goalDifference(){
        return this.goalsScored - this.goalsConceded;
    }
}
