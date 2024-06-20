package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        StringBuilder creatUsersTableSQL = new StringBuilder();
        creatUsersTableSQL.append("CREATE TABLE IF NOT EXISTS `users` (\n");
        creatUsersTableSQL.append("`id` INT NOT NULL AUTO_INCREMENT,\n");
        creatUsersTableSQL.append("`name` VARCHAR(45) NOT NULL,\n");
        creatUsersTableSQL.append("`lastName` VARCHAR(45) NOT NULL,\n");
        creatUsersTableSQL.append("`age` INT NOT NULL,\n");
        creatUsersTableSQL.append("PRIMARY KEY (`id`)) \n");
        creatUsersTableSQL.append("ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;");

        try (Statement statement = connection.createStatement()) {
            statement.execute(creatUsersTableSQL.toString());
            System.out.printf("\nТаблица %s была создана", "users");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUserTableSQL = "DROP TABLE IF EXISTS users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropUserTableSQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = "INSERT INTO `users` (name, lastName, age) VALUES (?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUserSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.printf("\nUser с именем %s был добавлен в базу данных", name);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserByIdSQL = "DELETE FROM users WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserByIdSQL)) {
            preparedStatement.setInt(1, (int) id);
            System.out.printf("\nПользователь с id = %s был удален", id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM users;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllUsersSQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                userList.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String clearUsersTableSQL = "TRUNCATE TABLE users;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearUsersTableSQL);
            System.out.println("\nТаблица users была полностью очищена");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
