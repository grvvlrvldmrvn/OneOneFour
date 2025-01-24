package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTheTable = """
                CREATE TABLE IF NOT EXISTS Users
                              (
                  id       INT AUTO_INCREMENT PRIMARY KEY,
                  name     VARCHAR(50) NOT NULL,
                  lastName VARCHAR(50) NOT NULL,
                  age      INT(3)      NOT NULL
                              );
                """;
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(createTheTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTheTable = """
                DROP TABLE IF EXISTS Users;
                """;
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(dropTheTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String getAllFromTheTable = "FROM User";
        List<User> userList = new ArrayList<>();
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            userList = session.createQuery(getAllFromTheTable, User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String deleteAllFromTable = "DELETE FROM User";
        try {
            Session session = Util.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createQuery(deleteAllFromTable).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}