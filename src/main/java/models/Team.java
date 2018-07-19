package models;

public abstract class Team {
    private int id;
    private String name;
    private Manager manager;
    private League league;
    private int points;
    private String teamLogo;
    private String homePitch;

    public Team(){}

    public Team(String name, Manager manager, League league, int points, String teamLogo){
        this.name = name;
        this.manager = manager;
        this.league = league;
        this.points = points;
        this.teamLogo = teamLogo;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getHomePitch() {
        return homePitch;
    }

    public void setHomePitch(String homePitch) {
        this.homePitch = homePitch;
    }
}
