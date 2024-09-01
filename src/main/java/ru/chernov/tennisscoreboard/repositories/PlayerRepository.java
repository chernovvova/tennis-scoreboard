package ru.chernov.tennisscoreboard.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.chernov.tennisscoreboard.models.Player;
import ru.chernov.tennisscoreboard.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerRepository implements CrudRepository<Player> {
    @Override
    public Optional<Player> findById(Long id) {
        Optional<Player> player = Optional.empty();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            player = Optional.of(session.get(Player.class, id));
            session.getTransaction().commit();
        }
        return player;
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            players = session.createQuery("from Player").list();
            session.getTransaction().commit();
        }
        return players;
    }

    @Override
    public void delete(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(player);
            session.getTransaction().commit();
        }
    }

    @Override
    public Player save(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return player;
    }

    @Override
    public void update(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.merge(player);
            session.getTransaction().commit();
        }
    }

    public Optional<Player> findByName(String name) {
        Optional<Player> player = Optional.empty();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "from Player where name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            player = Optional.of(query.uniqueResult());
            session.getTransaction().commit();
        }
        return player;
    }
}
