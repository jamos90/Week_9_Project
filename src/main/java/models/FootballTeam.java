package models;

import javax.persistence.*;

@Entity
@Table( name ="football_teams")
@Inheritance(strategy = InheritanceType.JOINED)


@DiscriminatorColumn(name= "sport_type")
public class FootballTeam extends Team {
    private int goalsScored;
    private int goalsConceded;


    public FootballTeam(String name, Manager manager, League league, int points, String teamLogo, int goalsScored, int goalsConceded, String location){
        super(name, manager,league,points,teamLogo,location);
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
    }

    public FootballTeam(){};

    @Column(name = "goals_scored")
    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    @Column(name = "goals_conceded")
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
