package com.example.SpringBootTMS.controller;


import com.example.SpringBootTMS.service.Service;
import com.example.SpringBootTMS.model.UsersApp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Service service;

    @GetMapping("")
    public String index() {
        return "/index";
    }
    @GetMapping(value = "/insert_user")
    public String user(Model model) {
        model.addAttribute("userApp", new UsersApp());
        return "addUser";
    }
    @PostMapping("/add")
    public String insert(@ModelAttribute("userApp") @Valid UsersApp userApp, BindingResult bindingResult
                          , ModelMap model) throws SQLException {
        if(bindingResult.hasErrors())
            return "addUser";
        model.addAttribute("age",userApp.getAge());
        model.addAttribute("login",userApp.getLogin());
        model.addAttribute("id",userApp.getId());
        model.addAttribute("message", service.putUser(userApp));
        return "user";
    }

    @GetMapping("/get_user/{id}")
    public ModelAndView get(@PathVariable String id,ModelMap model) throws SQLException {
        UsersApp usersApp = service.getByIdUser(id);
        if(usersApp!=null){
        model.addAttribute("id",usersApp.getId());
        model.addAttribute("login",usersApp.getLogin());
        model.addAttribute("age",usersApp.getAge());
        model.addAttribute("message","Successful");
            return new ModelAndView("user", model);}
        else return new ModelAndView("result","message","User isn't found ");

    }

    @GetMapping("/delete_user/{id}")
    public ModelAndView delete(@PathVariable String id) throws SQLException {
       return new ModelAndView("result", "message", service.deleteById(id));
    }

    @GetMapping("/update/{id}/{login}")
    public ModelAndView update(@PathVariable String id, @PathVariable String login) throws SQLException {
        return new ModelAndView("result", "message", service.updateLogin(id, login));
    }
    @ExceptionHandler(SQLException.class)
    public ModelAndView handlerSqlException(HttpServletRequest request, SQLException ex)
    {
        return new ModelAndView("sqlError","error", ex.getMessage());
    }
}
