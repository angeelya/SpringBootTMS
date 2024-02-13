package com.example.SpringBootTMS.controller;

import com.example.SpringBootTMS.model.UsersApp;
import com.example.SpringBootTMS.service.Service;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(Controller.class)
class ControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MockMvc mockMvc;
    @MockBean
    Service service;
    @Test
    void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    @Test
    void user() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/insert_user"))
                .andExpect(model().attributeExists("userApp"))
                .andExpect(view().name("addUser"));
    }

    @Test
    void insert() throws Exception {
        UsersApp usersApp = new UsersApp(1L,"Danila",25);
        when(service.putUser(usersApp)).thenReturn("Insert is successful");
        mockMvc.perform(post("/add")
                        .param("id", String.valueOf(usersApp.getId()))
                        .param("login", usersApp.getLogin())
                        .param("age", String.valueOf(usersApp.getAge())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("age"))
                .andExpect(model().attributeExists("login"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("user"));
        verify(service).putUser(usersApp);
    }


    @SneakyThrows
    @Test
    void get() {
        UsersApp usersApp = new UsersApp(1L,"Danila",25);
        String id="1";
        when(service.getByIdUser(id)).thenReturn(usersApp);
        mockMvc.perform(MockMvcRequestBuilders.get("/get_user/{id}",id))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("login"))
                .andExpect(model().attributeExists("age"))
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("user"));
        when(service.getByIdUser(id)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/get_user/{id}",id))
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("result"));
        verify(service,times(2)).getByIdUser(id);
    }

    @Test
    void delete() throws Exception {
        String id="1";
        when(service.deleteById(id)).thenReturn("Delete is successful");
        mockMvc.perform(MockMvcRequestBuilders.get("/delete_user/{id}",id))
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("result"));
        verify(service,times(1)).deleteById(id);
    }

    @Test
    void update() throws Exception {
        String login="Irina",id="1";
        when(service.updateLogin(id,login)).thenReturn("Update is successful");
        mockMvc.perform(MockMvcRequestBuilders.get("/update/{id}/{login}",id,login))
                .andExpect(model().attributeExists("message"))
                .andExpect(view().name("result"));
        verify(service,times(1)).updateLogin(id,login);
    }

}