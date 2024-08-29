package ru.chernov.tennisscoreboard.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;
import ru.chernov.tennisscoreboard.repositories.MatchRepository;
import ru.chernov.tennisscoreboard.repositories.PlayerRepository;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static final MatchRepository matchRepository = new MatchRepository();
    private static final PlayerRepository playerRepository = new PlayerRepository();

    static {
        Player player1 = new Player("К. АЛЬКАРАС");
        Player player2 = new Player("Ф. КОБОЛЛИ");
        matchRepository.save(new Match(player1, player2, player1));
    }

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
