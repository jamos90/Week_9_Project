package models;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="fixtures")

public class Fixture {
    private int id;
    private List<Team> teams;
    private String homeGoals;
    private String awayGoals;
    private int week;
    private String venue;
    private MatchReport matchReport;
    private League league;

    public Fixture(){}

    public Fixture(int week, String venue, League league){
        this.teams = new ArrayList<Team>();
        this.homeGoals = "";
        this.awayGoals = "";
        this.week = week;
        this.league = league;
        this.venue = venue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "teams_fixtures")
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Column(name = "home_goals")
    public String getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(String homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Column(name = "away_goals")
    public String getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(String awayGoals) {
        this.awayGoals = awayGoals;
    }

    public int convertGoalsToInteger(String goals){
        return Integer.parseInt(goals);
    }

    @Column(name = "week")
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Column(name = "venue")
    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @OneToOne(mappedBy = "fixture", fetch = FetchType.LAZY)
    public MatchReport getMatchReport() {
        return matchReport;
    }

    @ManyToOne
    @JoinColumn(name="league_id", nullable = false)
    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setMatchReport(MatchReport matchReport) {
        this.matchReport = matchReport;
    }
}
