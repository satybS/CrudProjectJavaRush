package CRUD.data;


import CRUD.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class HibernateUserRepository implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(HibernateUserRepository.class);

    private SessionFactory sessionFactory;
    private static final int limitResultsPerPage = 5;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully added. User info: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User info successfully updated. Updated user info: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        if (user != null)
            session.delete(user);
        logger.info("User was successfully deleted. User info: " + user);
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        logger.info("User successfully loaded. User info: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(Long page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User");
        query.setFirstResult((int)(page - 1) * limitResultsPerPage);
        query.setMaxResults(limitResultsPerPage);
        List<User> users = query.list();
        for (User curr : users) {
            logger.info("User list info: " + curr);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE NAME = :name");
        query.setParameter("name", name);
        List<User> users = query.list();
        for (User curr : users) {
            logger.info("User list info: " + curr);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(int age) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE AGE = :age");
        query.setParameter("age", age);
        List<User> users = query.list();
        for (User curr : users) {
            logger.info("User list info: " + curr);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(Date dateCreated) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dateCreated);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE " +
                "year(CREATEDDATE) = :year " +
                "and month(CREATEDDATE) = :month " +
                "and day(CREATEDDATE) = :day " +
                "and hour(CREATEDDATE) = :hour " +
                "and minute(CREATEDDATE) = :minute");
        query.setParameter("year", cal.get(Calendar.YEAR));
        query.setParameter("month", cal.get(Calendar.MONTH) + 1);
        query.setParameter("day", cal.get(Calendar.DAY_OF_MONTH));
        query.setParameter("hour", cal.get(Calendar.HOUR));
        query.setParameter("minute", cal.get(Calendar.MINUTE));
        List<User> users = query.list();
        for (User curr : users) {
            logger.info("User list info: " + curr);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAdmins() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("FROM User WHERE ISADMIN = 1").list();
        for (User curr : users) {
            logger.info("User list info: " + curr);
        }
        return users;
    }
}

