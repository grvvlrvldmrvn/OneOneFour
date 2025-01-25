package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exception.*;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    PreparedStatement preparedStatement;

    public UserDaoJDBCImpl() {

    }

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
            preparedStatement = util.getConnection().prepareStatement(createTheTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CreateTableException("Ошибка при создании таблицы", e);
        }
    }

    public void dropUsersTable() {
        String dropTheTable = """
                DROP TABLE IF EXISTS Users;
                """;
        try {
            preparedStatement = util.getConnection().prepareStatement(dropTheTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DropTableException("Ошибка при удалении таблицы", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertToTable = "INSERT INTO Users (name, lastName, age) " +
                               "VALUES (?,?,?)";
        try {
            preparedStatement = util.getConnection().prepareStatement(insertToTable);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SaveUserException("Ошибка при сохранении пользователя в таблицу ", e);
        }
    }

    public void removeUserById(long id) {
        String deleteFromTable = "DELETE FROM Users WHERE id = " + id;
        try {
            preparedStatement = util.getConnection().prepareStatement(deleteFromTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RemoveUserByIdException("Ошибка при удалении пользователя из таблицы", e);
        }
    }

    public List<User> getAllUsers() {
        String getAllFromTheTable = "SELECT * FROM Users";
        List<User> userList = new ArrayList<>();
        try {
            User user = new User();
            preparedStatement = util.getConnection().prepareStatement(getAllFromTheTable);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new GetAllUsersException("Ошибка при получении всех пользователей из таблицы", e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String deleteAllFromTable = "TRUNCATE TABLE Users";
        try {
            preparedStatement = util.getConnection().prepareStatement(deleteAllFromTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CleanUsersTableException("Ошибка при отчистке таблицы", e);
        }
    }
}