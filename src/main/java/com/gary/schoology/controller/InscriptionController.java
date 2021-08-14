package com.gary.schoology.controller;

import com.gary.schoology.config.Conexion;
import com.gary.schoology.dao.InscriptionDAO;
import com.gary.schoology.dao.StudentDAO;
import com.gary.schoology.model.Inscription;
import com.gary.schoology.model.Student;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InscriptionController {
    @RequestMapping("/inscriptions")
    public String page_inscriptions(Model model){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        InscriptionDAO inscriptionDAO = new InscriptionDAO(dataSource);
        List inscriptions = inscriptionDAO.list();
        model.addAttribute("listInscriptions",inscriptions);
        return "inscriptions";
    }

    @RequestMapping(value = "/inscriptions/add", method = RequestMethod.POST)
    public String add_inscription(@RequestParam int studentId, String code){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        InscriptionDAO inscriptionDAO = new InscriptionDAO(dataSource);
        inscriptionDAO.add(studentId,code);
        return "redirect:/inscriptions";
    }

    @RequestMapping(value = "/inscriptions/delete", method = RequestMethod.POST)
    public String delete_inscription(@ModelAttribute("inscription") Inscription inscription){
        Conexion conexion = new Conexion();
        DriverManagerDataSource dataSource = conexion.getConnection();
        InscriptionDAO inscriptionDAO = new InscriptionDAO(dataSource);
        inscriptionDAO.delete(inscription.getId_inscription());
        return "redirect:/inscriptions";
    }
}
