package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl user = new UserDaoHibernateImpl();

        user.createUsersTable();

        user.saveUser("Name1", "LastName1", (byte) 1);
        user.saveUser("Name2", "LastName2", (byte) 2);
        user.saveUser("Name3", "LastName3", (byte) 3);
        user.saveUser("Name4", "LastName4", (byte) 4);

        user.removeUserById(1);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();

        Util.getSessionFactory();
    }
}