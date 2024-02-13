package com.example.SpringBootTMS.service;

import com.example.SpringBootTMS.model.UsersApp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServiceTest {
    @Mock
    Statement statement;
    @Mock
    ResultSet resultSet;
    @InjectMocks
    Service service;

    @SneakyThrows
    @Test
    void getByIdUser() {
        final String sql = "select * from usersapp where id=1";
        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("login")).thenReturn("Elena");
        when(resultSet.getInt("age")).thenReturn(22);
        UsersApp expectedUser = new UsersApp(),actualUser;
        expectedUser.setId(1L);
        expectedUser.setLogin("Elena");
        expectedUser.setAge(22);
         actualUser = service.getByIdUser("1");
        assertEquals(expectedUser, actualUser);
        when(resultSet.next()).thenReturn(false);
        actualUser = service.getByIdUser("1");
        assertEquals(null,actualUser);
        verify(statement, times(2)).executeQuery(sql);
    }

    @SneakyThrows
    @Test
    void putUser() {
        final String sql = "insert into usersapp(login,age) value('Elena',22)";
        final String[] expectedMessage = {"Insert is successful","Insert isn't successful"};
        String actualMessage;
        when(statement.executeUpdate(sql)).thenReturn(1);
        UsersApp user = new UsersApp();
        user.setLogin("Elena");
        user.setAge(22);
        actualMessage = service.putUser(user);
        assertEquals(expectedMessage[0], actualMessage);
        when(statement.executeUpdate(sql)).thenReturn(0);
        actualMessage = service.putUser(user);
        assertEquals(expectedMessage[1],actualMessage);
        verify(statement, times(2)).executeUpdate(sql);
    }

        @SneakyThrows
        @Test
        void updateLogin () {
            final String sql = "update usersapp set login='Alina'where id=1";
            final String[] expectedMessage = {"Update is successful","Update  was not completed"};
            String actualMessage;
            when(statement.executeUpdate(sql)).thenReturn(1);
            actualMessage=service.updateLogin("1","Alina");
            assertEquals(expectedMessage[0],actualMessage);
            when(statement.executeUpdate(sql)).thenReturn(0);
            actualMessage=service.updateLogin("1","Alina");
            assertEquals(expectedMessage[1],actualMessage);
            verify(statement,times(2)).executeUpdate(sql);
        }

        @Test
        void deleteById () throws SQLException {
            final String sql = "delete from usersapp where id=1";
            final String[] expectedMessage = {"Delete is successful","Delete was not completed"};
            String actualMessage;
            when(statement.executeUpdate(sql)).thenReturn(1);
            actualMessage=service.deleteById("1");
            assertEquals(expectedMessage[0],actualMessage);
            when(statement.executeUpdate(sql)).thenReturn(0);
            actualMessage=service.deleteById("1");
            assertEquals(expectedMessage[1],actualMessage);
            verify(statement,times(2)).executeUpdate(sql);
        }
    }