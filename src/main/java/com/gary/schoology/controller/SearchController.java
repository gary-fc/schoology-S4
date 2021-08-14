package com.gary.schoology.controller;

import com.gary.schoology.config.Conexion;
import com.gary.schoology.dao.SearchDAO;
import com.gary.schoology.dao.StudentDAO;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @RequestMapping("/search")
    public String search_students(@RequestParam String q, Model model){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        SearchDAO searchDAO = new SearchDAO(dataSource);

        List students = searchDAO.list_students(q);

        List classes = searchDAO.list_classes(q);

        List inscriptions = searchDAO.list_inscriptions(q);

        model.addAttribute("listStudents",students);
        model.addAttribute("listClass",classes);
        model.addAttribute("listInscriptions",inscriptions);
        return "search";
    }


}
