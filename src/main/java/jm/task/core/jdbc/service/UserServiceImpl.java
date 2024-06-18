package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        String tableName = "`Users`";
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
            System.out.printf("\nТаблица %s была создана", "new_table");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
       String dropUsersTable = "DROP TABLE IF EXISTS `Users`;";

        try (Connection conn = Util.getConnection()) {
            Statement st =conn.createStatement();
            st.execute(dropUsersTable);
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
        System.out.println(stringBuilder);
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

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
