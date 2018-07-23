package db;

import models.Fixture;
import models.League;
import models.Team;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public class DBFixture {

    private static Session session;

    public static List<Fixture> sorFixturesByPWeek() {


        List<Fixture> fixtures = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(Fixture.class);
            cr.addOrder(Order.desc("week"));
            fixtures = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return fixtures;
    }




}
