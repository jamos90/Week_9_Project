
import models.Fixture;
import models.League;
import models.LeagueType;
import models.Team;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LeagueTest {
    League league;
    Fixture fixture;
    Team team;
    LeagueType leagueType;

    @Before
    public void setUp(){
        league = new League("Edinburgh Schools", LeagueType.SCHOOLS, "Midlothian");
        fixture = new Fixture(1,"Edinburgh Football Club", league);
    }

    @Test
    public void hasName(){
        assertEquals("Edinburgh Schools", league.getName());
    }

    @Test
    public void hasLeagueType(){
        assertEquals(LeagueType.SCHOOLS, league.getLeagueType());
    }
}
