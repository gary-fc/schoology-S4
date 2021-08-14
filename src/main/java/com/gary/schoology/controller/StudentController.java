package com.gary.schoology.controller;

import com.gary.schoology.config.Conexion;
import com.gary.schoology.dao.ClassDAO;
import com.gary.schoology.dao.StudentDAO;
import com.gary.schoology.model.Student;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @RequestMapping("/students")
    public String page_students(Model model){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        List students = studentDAO.list();
        model.addAttribute("listStudents",students);
        return "students";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.POST)
    public String add_students(@ModelAttribute("student") Student student){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        studentDAO.add(student);
        return "redirect:/students";
    }


    @RequestMapping(value = "/students/update", method = RequestMethod.POST)
    public String edit_students(@ModelAttribute("student") Student student){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        studentDAO.edit(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/students/delete", method = RequestMethod.POST)
    public String delete_students(@ModelAttribute("student") Student student){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        studentDAO.delete(student);
        return "redirect:/students";
    }
}

@RestController
class RestStudentController{
    @RequestMapping(value = "api/student/classes", method = RequestMethod.GET)
    @ResponseBody
    public List get_class_by_student(@RequestParam String idClass){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        List classes = studentDAO.listStudentClass(idClass);
        return classes;
    }
}
