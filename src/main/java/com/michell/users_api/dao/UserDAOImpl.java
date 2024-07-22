package com.michell.users_api.dao;

import com.michell.users_api.entity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(User.class, id);
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        // Save method is deprecated since javadoc 6.0
        currentSession.persist(user);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("delete from User where id=:idUser", User.class);
        query.setParameter("idUser", id);
        query.executeUpdate();
    }
}
