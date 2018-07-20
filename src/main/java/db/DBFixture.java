package db;

import models.Fixture;
import models.League;

public class DBFixture {

    League league;
    Fixture fixture;


    public static void generateFixtures(League league){
        league.fixtureGenerator(true, league.getTeams(), league);
        DBHelper.save(league);
    }


}
