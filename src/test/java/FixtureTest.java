import db.DBHelper;
import models.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FixtureTest {

    Fixture fixture;
    FootballTeam footballTeam;
    Manager manager;
    League league;
    MatchReport matchReport1;


    @Before
    public void before() {
        manager = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");

        league = new League("North of England Regional Division", LeagueType.NATIONAL, "Lancashire");

        footballTeam = new FootballTeam("Bolton Wanderers", manager, league, "nothing", "Bolton");

        fixture = new Fixture(1, "Liberty Stadium", league);

        matchReport1 = new MatchReport(fixture, "Wanderers slaughtered in humiliating rout!", "Same", "logo");
    }

    @Test
    public void hasWeek(){
        assertEquals(1, fixture.getWeek());
    }


}
