package ru.chernov.tennisscoreboard.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.models.Player;
import ru.chernov.tennisscoreboard.services.SaveMatchService;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static final SaveMatchService saveMatchService = new SaveMatchService();

    static {
        Player player1 = new Player("Новак Джокович");
        Player player2 = new Player("Рафаэль Надаль");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Новак Джокович");
        player2 = new Player("Рафаэль Надаль");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Новак Джокович");
        player2 = new Player("Рафаэль Надаль");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Роджер Федерер");
        player2 = new Player("Даниил Медведев");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Новак Джокович");
        player2 = new Player("Роджер Федерер");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Рафаэль Надаль");
        player2 = new Player("Новак Джокович");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Стефанос Циципас");
        player2 = new Player("Рафаэль Надаль");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Карен Хачанов");
        player2 = new Player("Каспер Рууд");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Каспер Рууд");
        player2 = new Player("Стефанос Циципас");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Хумберт Гуркач");
        player2 = new Player("Андре Агасси");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Андре Агасси");
        player2 = new Player("Каспер Рууд");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Арина Соболенко");
        player2 = new Player("Серена Уильямс");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Бьорн Борг");
        player2 = new Player("Пит Сампрас");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Маттео Берреттини");
        player2 = new Player("Бьорн Борг");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Маттео Берреттини");
        player2 = new Player("Бьорн Борг");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Маттео Берреттини");
        player2 = new Player("Пит Сампрас");
        saveMatchService.saveMatch(new Match(player1, player2, player1));

        player1 = new Player("Андре Агасси");
        player2 = new Player("Бьорн Борг");
        saveMatchService.saveMatch(new Match(player1, player2, player2));

        player1 = new Player("Маттео Берреттини");
        player2 = new Player("Стефанос Циципас");
        saveMatchService.saveMatch(new Match(player1, player2, player1));
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
