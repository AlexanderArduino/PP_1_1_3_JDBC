package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        String tableName = "users";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ")
                .append(tableName).append(" (")
                .append("  `id` int NOT NULL AUTO_INCREMENT,\n")
                .append("  `name` varchar(45) NOT NULL,\n")
                .append("  `lastName` varchar(45) NOT NULL,\n")
                .append("  `age` int NOT NULL, \n")
                .append("  PRIMARY KEY (`id`)\n").append(") ")
                .append("ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;");
//        String createUserTable = "CREATE TABLE IF NOT EXISTS `new_table` (\n" +
//                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
//                "  `name` varchar(45) NOT NULL,\n" +
//                "  `lastName` varchar(45) NOT NULL,\n" +
//                "  PRIMARY KEY (`id`)\n" +
//                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;";

        try (Connection conn = Util.getConnection()){
            Statement st =conn.createStatement();
            st.execute(stringBuilder.toString());
            System.out.printf("\nТаблица %s была создана", tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String tableName = "users";
        String dropUsersTable = "DROP TABLE IF EXISTS " + tableName + ";";

        try (Connection conn = Util.getConnection()) {
            Statement st =conn.createStatement();
            st.execute(dropUsersTable);
            System.out.println("\nТаблица " + tableName + " была удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `users` (name, lastName, age) VALUES (")
                        .append("\"").append(name).append("\", ")
                        .append("\"").append(lastName).append("\", ")
                        .append(age).append(");");
//        stringBuilder.append("INSERT INTO `users` (`name`, `lastName`, `age`) VALUES ('Gosha', 'Pechkin', '20');");
        try (Connection conn = Util.getConnection()){
            Statement st =conn.createStatement();
            st.execute(stringBuilder.toString());
            System.out.printf("\nUser с именем %s был добавлен в базу данных", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM `users` WHERE id = " + id + ";";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(query);
            System.out.println("\nПользователь с id = " + id + " был удален");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "select * FROM `users`;";

        try (Connection conn = Util.getConnection(); Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                User user = new User();
                user.setId((long)resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte)resultSet.getInt(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("\nТаблица users была полностью очищена");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
