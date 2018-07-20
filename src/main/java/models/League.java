package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="leagues")
public class League {
    private int id;
    private String name;
    private LeagueType leagueType;
    private List<Team> teams;
    private List<Fixture> fixtures;
    private String region;

    public League() {
    }

    public League(String name, LeagueType leagueType, String region) {
        this.name = name;
        this.leagueType = leagueType;
        this.teams = new ArrayList<Team>();
        this.fixtures = new ArrayList<Fixture>();
        this.region = region;
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "league_type")
    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public String returnLeagueTypeFromEnum() {
        return this.leagueType.getLeagueType();
    }

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int teamCount() {
        return this.teams.size();
    }

    public void addToTeams(Team team) {
        this.teams.add(team);
    }

    public void removeTeams(Team team) {
        this.teams.remove(team);
    }

    public void clearTeams() {
        this.teams.clear();
    }

    public boolean leagueContainsTeam(Team team) {
        if (this.teams.contains(team)) {
            return true;
        } else return false;
    }

    public int fixtureCount() {
        return this.fixtures.size();
    }

    public void addToFixtures(Fixture fixture){
        this.fixtures.add(fixture);
    }

    public void removeFromFixtures(Fixture fixture){
        this.fixtures.remove(fixture);
    }

    public void clearFixtures(){
        this.fixtures.clear();
    }

    public boolean leagueContiansFixture(Fixture fixture){
        if(this.fixtures.contains(fixture)){
            return true;
        }
        else return  false;
    }

    public List<List<Fixture>> fixtureGenerator(Boolean reverseFixtures, List<Team> teams){
        //Set integer variable called number of teams = teams.size()
        int numberofTeams = teams.size();
        //ghost = false
        Boolean ghost = false;
        // if number of teams is odd incrment no of teams by 1 and set ghost to true
        if (teams.size() % 2 != 0){
            ghost = true;
        }
        //

    }

}



