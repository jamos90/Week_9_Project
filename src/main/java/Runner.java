import db.DBHelper;
import models.*;

public class Runner {

    public static void main(String[] args) {


        Manager manager = new Manager("Zsolt Poboda-Salai", "07610335467", "Zsolt@hungarianmail.hu");
        DBHelper.save(manager);

        League league = new League("North of England Regional Division", LeagueType.NATIONAL, "Lancashire");
        DBHelper.save(league);

        FootballTeam footballTeam = new FootballTeam("Bolton Wanderers", manager, league, "nothing", "Bolton");
        footballTeam.setGoalsConceded(120);
        footballTeam.setGoalsScored(56);
        DBHelper.save(footballTeam);

        Fixture fixture = new Fixture(1, league);
        DBHelper.save(fixture);

        MatchReport matchReport1 = new MatchReport(fixture, "Wanderers slaughtered in humiliating rout!", "Same", "logo");
        DBHelper.save(matchReport1);




    }
}
