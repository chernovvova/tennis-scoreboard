package ru.chernov.tennisscoreboard.repositories;

import org.hibernate.Session;
import ru.chernov.tennisscoreboard.models.Match;
import ru.chernov.tennisscoreboard.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchRepository implements CrudRepository<Match>{
    @Override
    public Optional<Match> findById(Long id) {
        Optional<Match> match = Optional.empty();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            match = Optional.of(session.get(Match.class, id));
            session.getTransaction().commit();
        }
        return Optional.empty();
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            matches = session.createQuery("from Match").list();
            session.getTransaction().commit();
        }
        return matches;
    }

    @Override
    public void delete(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(match);
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Match match) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Match match) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(match);
            session.getTransaction().commit();
        }
    }
}
