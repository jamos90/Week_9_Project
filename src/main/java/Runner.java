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
        DBHelper.deleteAll(Fixture.class);
        DBHelper.deleteAll(Manager.class);


        Manager manager = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager);

        Manager manager2 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager2);

        Manager manager3 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager3);

        Manager manager4 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager4);

        Manager manager5 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager5);

        Manager manager6 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager6);

        Manager manager7 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager7);

        Manager manager8 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager8);

        Manager manager9 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager9);

        Manager manager10 = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager10);

        League league = new League("North of England Regional Division", LeagueType.NATIONAL, "Lancashire");
        DBHelper.save(league);

        FootballTeam homefootballTeam = new FootballTeam("Bolton Wanderers", manager, league, "nothing", "Bolton");
        DBHelper.save(homefootballTeam);

        FootballTeam awayfootballTeam = new FootballTeam("Dagenham and Redbridge", manager2, league, "yes", "Dagenham");
        DBHelper.save(awayfootballTeam);

        FootballTeam thirdFootballTeam = new FootballTeam("Burton Albion", manager3, league, "yes", "Burton");
        DBHelper.save(thirdFootballTeam);

        FootballTeam fourthfootballTeam = new FootballTeam("Edinburgh City", manager4, league, "no", "Edinburgh");
        DBHelper.save(fourthfootballTeam);

        FootballTeam fithfootballTeam = new FootballTeam("Glasgow City", manager5, league, "no", "Edinburgh");
        DBHelper.save(fithfootballTeam);

        FootballTeam sixthfootballTeam = new FootballTeam("Dundee City", manager6, league, "no", "Edinburgh");
        DBHelper.save(sixthfootballTeam);

        FootballTeam seventhfootballTeam = new FootballTeam("Aberdeen City", manager7, league, "no", "Edinburgh");
        DBHelper.save(seventhfootballTeam);

        FootballTeam eighthfootballTeam = new FootballTeam("FortWilliam City", manager8, league, "no", "Edinburgh");
        DBHelper.save(eighthfootballTeam);

        FootballTeam ninethfootballTeam = new FootballTeam("Inverness City", manager9, league, "no", "Edinburgh");
        DBHelper.save(ninethfootballTeam);

        FootballTeam tenthfootballTeam = new FootballTeam("Random City", manager10, league, "no", "Edinburgh");
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
        DBHelper.update(league);

        for (Fixture fixture : league.getFixtures()) {
            DBHelper.save(fixture);
        }

        Fixture f = new Fixture(4, 2, league);
        f.addAwayTeamToFixture(awayfootballTeam);
        f.addHomeTeamToFixture(fourthfootballTeam);
        DBHelper.save(f);

        MatchReport report1 = new MatchReport(f, "Dagenham pull off shock victory at Edinburgh", "Against all expectations, Dagenham and Redbridge eked out a battling win away at Edinburgh, their first away points of the season.", "n");
        DBHelper.save(report1);
        f.setMatchReport(report1);
        DBHelper.update(f);


        List<Manager> allManagers = DBHelper.getAll(Manager.class);
        List<MatchReport> allReports = DBHelper.getAll(MatchReport.class);
        List<FootballTeam> allFootballTeams = DBHelper.getAll(FootballTeam.class);
        List<League> allLeagues = DBHelper.getAll(League.class);
        List<Fixture> allFixtures = DBHelper.getAll(Fixture.class);

        Manager foundManager = DBHelper.find(manager.getId(), Manager.class);
        Fixture Foundfixture = DBHelper.find(f.getId(), Fixture.class);

        List<Fixture> FixturesForOurLeague = DBLeague.getFixturesForLeague(league);



//        DBLeague.getFullSeasonFixtures(2, league);

        List<Fixture> updatedLeagueFixtures = DBLeague.getFixturesForLeague(league);

        List<Manager> mangers = DBHelper.getAll(Manager.class);



    }
}