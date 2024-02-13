package com.example.SpringBootTMS.service;

import com.example.SpringBootTMS.model.UsersApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

@org.springframework.stereotype.Service
public class Service {
    private Connection connection;
    private Statement statement;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String message;

    private final String DRIVER = "com.mysql.cj.jdbc.Driver", DB_URL = "jdbc:mysql://localhost/", DATABASE_NAME = "work20", LOGIN = "root", PASS = "mysql";

    public Service() {
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
            statement = connection.createStatement();
            statement.executeUpdate("create database IF NOT EXISTS " + DATABASE_NAME);
            statement.close();
            connection.close();
            LOGGER.info("Database created");
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void createTable() {
        try {
            connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, LOGIN, PASS);
            statement = connection.createStatement();
            statement.executeUpdate("create table IF NOT EXISTS usersapp (id bigint primary key auto_increment ,login varchar(200),age int)");
            LOGGER.info("Connection and Table created");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public UsersApp getByIdUser(String id) throws SQLException {
        UsersApp usersApp;
        ResultSet userInformation = statement.executeQuery("select * from usersapp where id=" + id);
        if (userInformation.next() && userInformation.getLong("id") != 0) {
            usersApp = new UsersApp();
            usersApp.setId(userInformation.getLong("id"));
            usersApp.setLogin(userInformation.getString("login"));
            usersApp.setAge(userInformation.getInt("age"));
            LOGGER.info("User got: " + usersApp);
        } else {
            usersApp = null;
            LOGGER.info("User didn't find: null");
        }
        return usersApp;
    }

    public String putUser(UsersApp usersApp) throws SQLException {
        message = "Insert isn't successful";
        if (statement.executeUpdate("insert into usersapp(login,age) value('" + usersApp.getLogin() + "'," + usersApp.getAge() + ")") > 0)
            message = "Insert is successful";
        LOGGER.info(message);
        return message;
    }

    public String updateLogin(String id, String login) throws SQLException {
        message = "Update  was not completed";
        if (statement.executeUpdate("update usersapp set login='" + login + "'where id=" + id) > 0)
            message = "Update is successful";
        LOGGER.info(message);
        return message;
    }

    public String deleteById(String id) throws SQLException {
        message = "Delete was not completed";
        if (statement.executeUpdate("delete from usersapp where id=" + id) > 0)
            message = "Delete is successful";
        LOGGER.info(message);
        return message;
    }
}

