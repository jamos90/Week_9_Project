import db.DBFixture;
import db.DBHelper;
import models.*;

public class Runner {



    public static void main(String[] args) {


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

        Fixture fixture = new Fixture(1, league);
        DBHelper.save(fixture);

        DBFixture.generateFixtures(league);

        MatchReport matchReport1 = new MatchReport(fixture, "Wanderers slaughtered in humiliating rout!", "Same", "logo");
        DBHelper.save(matchReport1);




    }
}
