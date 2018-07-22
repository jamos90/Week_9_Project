package models;

public enum LeagueType {
    PROFESSIONAL("Professional"),
    SCHOOLS("Schools"),
    AMATEUR("Amateur"),
    NATIONAL("National"),
    INTERNATIONAL("International");

    private final String leagueType;

     LeagueType(String type){
        this.leagueType = type;
    }

    public String getLeagueType() {
        return leagueType;
    }


}
