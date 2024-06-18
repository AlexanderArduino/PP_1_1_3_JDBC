package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь


        UserService us = new UserServiceImpl();
        us.dropUsersTable();
        us.createUsersTable();
        us.saveUser("Vasya", "Pupkin", (byte)28);
        us.saveUser("Petya", "Pechkin", (byte)34);
        us.saveUser("Kolya", "Nikonov", (byte)17);
        us.saveUser("Dima", "Grachev", (byte)8);


    }
}
