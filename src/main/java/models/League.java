package models;

import java.util.ArrayList;
import java.util.List;

public class League {
    private int id;
    private String name;
    private LeagueType leagueType;
    private List<Team> teams;
    private List<Fixture> fixtures;
    private String region;

    public League(){}

    public League(String name, LeagueType leagueType, String region){
        this.name = name;
        this.leagueType = leagueType;
        this.teams = new ArrayList<Team>();
        this.fixtures = new ArrayList<Fixture>();
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(LeagueType leagueType) {
        this.leagueType = leagueType;
    }

    public String getLeagueTypeFromEnum(){
        return this.leagueType.getLeagueType();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
