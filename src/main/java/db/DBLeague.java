package db;

import models.Fixture;
import models.League;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBLeague {

    private static Session session;
    private static Transaction transaction;


    public static List<Fixture> getFixturesForLeague(League league){
            session = HibernateUtil.getSessionFactory().openSession();
            List<Fixture> results = null;
            try {
                Criteria cr = session.createCriteria(Fixture.class);
                cr.add(Restrictions.eq("league", league));
                results = cr.list();
            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
            return results;
        }
    }


