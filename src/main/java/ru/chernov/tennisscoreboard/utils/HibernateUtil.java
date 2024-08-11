package ru.chernov.tennisscoreboard.utils;

import org.h2.engine.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml").
                        addAnnotatedClass(Player.class).
                        addAnnotatedClass(Match.class).
                        buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if(sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
