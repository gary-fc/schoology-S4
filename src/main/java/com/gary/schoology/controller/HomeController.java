package com.gary.schoology.controller;

import com.gary.schoology.config.Conexion;
import com.gary.schoology.dao.ClassDAO;
import com.gary.schoology.dao.GraphicDAO;
import com.gary.schoology.dao.StudentDAO;
import com.gary.schoology.model.Graphic;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        StudentDAO studentDAO = new StudentDAO(dataSource);
        List students = studentDAO.list();
        model.addAttribute("listStudents",students);
        return "index";
    }
}

@RestController
class RestGraphicController{
    @RequestMapping(value = "api/graphic/total", method = RequestMethod.GET)
    @ResponseBody
    public Graphic get_class_by_student(){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        GraphicDAO graphicDAO = new GraphicDAO(dataSource);
        return graphicDAO.get_data();
    }
}
