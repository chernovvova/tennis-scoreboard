package ru.chernov.tennisscoreboard.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
    public Match save(Match match) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return match;
    }

    @Override
    public void update(Match match) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(match);
            session.getTransaction().commit();
        }
    }

    public List<Match> getAllPagination(int limit, int offset) {
        List<Match> matches = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Match ORDER BY id DESC", Match.class);
            matches = query.setMaxResults(limit).setFirstResult(offset).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List<Match> getByPlayerNamePagination(String name, int limit, int offset) {
        List<Match> matches = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Match where player1.name like:name or player2.name like:name ORDER BY id DESC", Match.class);
            query.setParameter("name", "%" + name + "%");
            matches = query.setMaxResults(limit).setFirstResult(offset).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public long getByPlayerNameUnique(String name) {
        long uniqueResults = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT count(*) from Match where player1.name like:name or player2.name like:name", Match.class);
            query.setParameter("name", "%" + name + "%");
            uniqueResults = (long) query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueResults;
    }

    public long getAllUnique() {
        long uniqueResults = 0;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT count(*) from Match", Match.class);
            uniqueResults = (long) query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueResults;
    }
}
