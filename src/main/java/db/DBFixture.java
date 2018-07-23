package db;

import models.Fixture;
import models.League;
import models.Team;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBFixture {

    League league;
    Fixture fixture;
    private static Session session;



    public static void generateFixtures(League league){
//       List<List<Fixture>> tempList =  league.fixtureGenerator(true);
//       league.getFixtureFromListOfListOfFixturesAndAddToFixtureList(league);
//        DBHelper.update(league);
    }

    public static List<Fixture> findByWeek(int week) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Fixture> fixtures = null;
        try{
            Criteria cr = session.createCriteria(Fixture.class);
            cr.add(Restrictions.eq("week", week));
            fixtures = cr.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return fixtures;
    }

}
