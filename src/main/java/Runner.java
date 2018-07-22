import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import db.DBFixture;
import db.DBHelper;
import db.DBLeague;
import models.*;

import java.util.List;

public class Runner {



    public static void main(String[] args) {

        DBHelper.deleteAll(Manager.class);
        DBHelper.deleteAll(Team.class);
        DBHelper.deleteAll(FootballTeam.class);
        DBHelper.deleteAll(MatchReport.class);
        DBHelper.deleteAll(League.class);
        DBHelper.deleteAll(Fixture.class);
        DBHelper.deleteAll(League.class);


        Manager manager = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager);

        League league = new League("North of England Regional Division", LeagueType.NATIONAL, "Lancashire");
        DBHelper.save(league);

        FootballTeam homefootballTeam = new FootballTeam("Bolton Wanderers", manager, league, "nothing", "Bolton");
        DBHelper.save(homefootballTeam);

        FootballTeam awayfootballTeam = new FootballTeam("Dagenham and Redbridge", manager, league, "yes", "Dagenham");
        DBHelper.save(awayfootballTeam);

        FootballTeam thirdFootballTeam = new FootballTeam("Burton Albion", manager, league, "yes", "Burton");
        DBHelper.save(thirdFootballTeam);

        FootballTeam fourthfootballTeam = new FootballTeam("Edinburgh City", manager, league, "no", "Edinburgh");
        DBHelper.save(fourthfootballTeam);

        FootballTeam fithfootballTeam = new FootballTeam("Glasgow City", manager, league, "no", "Edinburgh");
        DBHelper.save(fithfootballTeam);

        FootballTeam sixthfootballTeam = new FootballTeam("Dundee City", manager, league, "no", "Edinburgh");
        DBHelper.save(sixthfootballTeam);

        FootballTeam seventhfootballTeam = new FootballTeam("Aberdeen City", manager, league, "no", "Edinburgh");
        DBHelper.save(seventhfootballTeam);

        FootballTeam eighthfootballTeam = new FootballTeam("FortWilliam City", manager, league, "no", "Edinburgh");
        DBHelper.save(eighthfootballTeam);

        FootballTeam ninethfootballTeam = new FootballTeam("Inverness City", manager, league, "no", "Edinburgh");
        DBHelper.save(ninethfootballTeam);

        FootballTeam tenthfootballTeam = new FootballTeam("Random City", manager, league, "no", "Edinburgh");
        DBHelper.save(tenthfootballTeam);

        league.addToTeams(awayfootballTeam);
        league.addToTeams(homefootballTeam);
        league.addToTeams(thirdFootballTeam);
        league.addToTeams(fourthfootballTeam);
        league.addToTeams(fithfootballTeam);
        league.addToTeams(sixthfootballTeam);
        league.addToTeams(seventhfootballTeam);
        league.addToTeams(eighthfootballTeam);
        league.addToTeams(ninethfootballTeam);
        league.addToTeams(tenthfootballTeam);


        league.generateFixtures(true);



        for (Fixture fixture : league.getFixtures()) {
            DBHelper.save(fixture);
        }

        Fixture f = new Fixture(4,league);
        f.addAwayTeamToFixture(awayfootballTeam);
        f.addHomeTeamToFixture(fourthfootballTeam);
        DBHelper.save(f);

        MatchReport report1 = new MatchReport(f, "Dagenham pull of shock victory at Edinburgh", "Against all expectations, Dagenham and Redbridge eked out a battling win away at Edinburgh, their first away points of the season.", "n");
        DBHelper.save(report1);

        List<Fixture> allFixtures = DBHelper.getAll(Fixture.class);
        List<Manager> allManagers = DBHelper.getAll(Manager.class);
        List<MatchReport> allReports = DBHelper.getAll(MatchReport.class);
        List<FootballTeam> allFootballTeams = DBHelper.getAll(FootballTeam.class);
        List<League> allLeagues = DBHelper.getAll(League.class);

        List<Fixture> FixturesForOurLeague = DBLeague.getFixturesForLeague(league);



//        DBLeague.getFullSeasonFixtures(2, league);
//        List<Fixture> updatedLeagueFixtures = DBLeague.getFixturesForLeague(league);


    }
}
