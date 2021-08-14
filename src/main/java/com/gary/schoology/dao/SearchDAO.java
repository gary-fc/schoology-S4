package com.gary.schoology.dao;

import com.gary.schoology.interfaces.SearchInterface;
import com.gary.schoology.model.Class;
import com.gary.schoology.model.Inscription;
import com.gary.schoology.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class SearchDAO implements SearchInterface {

    private JdbcTemplate jdbcTemplate;

    public SearchDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List list_students(String q) {
        String sql;
        if(isNumeric(q)){
            sql = "SELECT * FROM tb_student WHERE studentId = " + q +" OR lastName LIKE '%" + q + "%' OR firstName LIKE '%" + q + "%'";
        }else{
            sql = "SELECT * FROM tb_student WHERE lastName LIKE '%" + q + "%'" + " OR firstName LIKE '%" + q + "%'";
        }

        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            Integer studentId = rs.getInt("studentId");
            String lastName = rs.getString("lastName");
            String firstName = rs.getString("firstName");
            return new Student(studentId,lastName,firstName);

        };

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List list_classes(String q) {
        String sql;
        if(isNumeric(q)){
            sql = "SELECT * FROM tb_class WHERE code = " + q +" OR classTitle LIKE '%" + q + "%' OR classDescription LIKE '%" + q + "%'";
        }else{
            sql = "SELECT * FROM tb_class WHERE classTitle LIKE '%" + q + "%'" + " OR classDescription LIKE '%" + q + "%'";
        }
        RowMapper<Class> rowMapper = (rs, rowNum) -> {
            String code = rs.getString("code");
            String classTitle = rs.getString("classTitle");
            String classDescription = rs.getString("classDescription");
            return new Class(code,classTitle,classDescription);

        };

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List list_inscriptions(String q) {
        String sql;
        if(isNumeric(q)){
            sql = "SELECT * FROM tb_inscription WHERE id_inscription = " + q +" OR studentId LIKE '%" + q + "%' OR code LIKE '%" + q + "%'";
        }else{
            sql = "SELECT * FROM tb_inscription WHERE studentId LIKE '%" + q + "%'" + " OR code LIKE '%" + q + "%'";
        }
        RowMapper<Inscription> rowMapper = (rs, rowNum) -> {
            Integer id_inscription = rs.getInt("id_inscription");
            Integer studentId = rs.getInt("studentId");
            String code = rs.getString("code");
            return new Inscription(id_inscription,studentId,code);

        };

        return jdbcTemplate.query(sql,rowMapper);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
