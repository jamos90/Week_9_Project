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
    private int homeGoals;
    private int awayGoals;
    private int week;
    private String venue;
    private MatchReport matchReport;
    private League league;

    public Fixture(){}

    public Fixture(int homeGoals, int awayGoals, int week, String venue){
        this.teams = new ArrayList<Team>();
        this.week = week;
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
    @JoinTable(name = "teams_fixtures",
            joinColumns = {@JoinColumn(name = "fixture_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "team_id", nullable = false, updatable = false)})
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Column(name = "home_goals")
    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Column(name = "away_goals")
    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
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
