import models.Fixture;
import models.League;
import models.LeagueType;
import models.MatchReport;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MatchReportTest{

    MatchReport matchReport;
    Fixture fixture;
    League league;

    @Before
    public void setUp(){
        fixture = new Fixture(1,"Edinburgh",league);
        league = new League("Edinburgh Schools", LeagueType.SCHOOLS, "Midlothian");
        matchReport = new MatchReport(fixture,"one", "two", "pic.jpg");
    }
    @Test
    public void hasFixture(){
        assertEquals(fixture, matchReport.getFixture());
    }

}
