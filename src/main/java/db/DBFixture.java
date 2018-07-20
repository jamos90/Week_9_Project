package db;

import models.Fixture;
import models.League;

import java.util.List;

public class DBFixture {

    League league;
    Fixture fixture;


    public static void generateFixtures(League league){
       List<List<Fixture>> tempList =  league.fixtureGenerator(true, league.getTeams(), league);
       league.getFixtureFromListOfListOfFixturesAndAddToFixtureList(league);
        DBHelper.update(league);
    }




}
