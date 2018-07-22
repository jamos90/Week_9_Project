package db;

import models.Fixture;
import models.League;
import models.Team;
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

        public  static List<Team> getTeamsForALeaugue(League league){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Team> teams = null;
                try{
                    Criteria cr = session.createCriteria(Team.class);
                    cr.add(Restrictions.eq("league", league));
                    teams = cr.list();
                }
                catch (HibernateException e){
            e.printStackTrace();
                }
                finally {
            session.close();
                }
                return teams;
        }

//        public static List<Fixture> getFullSeasonFixtures(int numberOfHeadToHeads, League league){
//        for (int i = 1; i <= numberOfHeadToHeads; i++){
//            league.generateFixtures(true);
//        }
//        DBHelper.update(league);
//        return league.getFixtures();
//        }
    }


