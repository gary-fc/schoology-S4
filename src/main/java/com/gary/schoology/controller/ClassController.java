package com.gary.schoology.controller;

import com.gary.schoology.config.Conexion;
import com.gary.schoology.dao.ClassDAO;
import com.gary.schoology.dao.StudentDAO;
import com.gary.schoology.model.Class;
import com.gary.schoology.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClassController {
    @RequestMapping("/class")
    public String page_class(Model model){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        ClassDAO classDAO = new ClassDAO(dataSource);
        List classes = classDAO.list();
        model.addAttribute("listClass",classes);
        return "class";
    }

    @RequestMapping(value = "/class/add", method = RequestMethod.POST)
    public String add_class(@ModelAttribute("class") Class classes){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        ClassDAO  classDAO = new ClassDAO(dataSource);
        classDAO.add(classes);
        return "redirect:/class";
    }


    @RequestMapping(value = "/class/update", method = RequestMethod.POST)
    public String edit_class(@ModelAttribute("class") Class classes){
        System.out.println(classes.getCode());
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        ClassDAO classDAO = new ClassDAO(dataSource);
        classDAO.edit(classes);
        return "redirect:/class";
    }

    @RequestMapping(value = "/class/delete", method = RequestMethod.POST)
    public String delete_class(@ModelAttribute("class") Class classes){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        ClassDAO classDAO = new ClassDAO(dataSource);
        classDAO.delete(classes);
        return "redirect:/class";
    }
}

@RestController
class RestClassController{
    @RequestMapping(value = "api/class/students", method = RequestMethod.GET)
    @ResponseBody
    public List get_class_by_student(@RequestParam int idStudent){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        ClassDAO classDAO = new ClassDAO(dataSource);
        List classes = classDAO.listClassStudent(idStudent);
        return classes;
    }
}
