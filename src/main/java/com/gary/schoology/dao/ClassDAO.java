package com.gary.schoology.dao;

import com.gary.schoology.interfaces.ClassInterface;
import com.gary.schoology.model.Class;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class ClassDAO implements ClassInterface {

    private JdbcTemplate jdbcTemplate;

    public ClassDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List list() {
        String sql = "SELECT * FROM tb_class";
        RowMapper<Class> rowMapper = (rs, rowNum) -> {
            String code = rs.getString("code");
            String title = rs.getString("classTitle");
            String description = rs.getString("classDescription");
            return new Class(code,title,description);

        };
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Class get(int id) {
        return null;
    }

    @Override
    public int add(Class classes) {
        String sql = "INSERT INTO tb_class(code,classTitle,classDescription) VALUES (?,?,?)";
        return jdbcTemplate.update(sql,classes.getCode(),classes.getClassTitle(),classes.getClassDescription());
    }

    @Override
    public int edit(Class classes) {
        String sql = "UPDATE tb_class SET code = ?, classTitle = ?, classDescription = ? WHERE code = ?";
        return jdbcTemplate.update(sql,classes.getCode(),classes.getClassTitle(),classes.getClassDescription(),classes.getCode());
    }

    @Override
    public int delete(Class classes) {
        String sql = "DELETE FROM tb_class WHERE code = ?";
        return jdbcTemplate.update(sql,classes.getCode());
    }

    @Override
    public List listClassStudent(int id) {
        String sql = "SELECT * FROM tb_class JOIN tb_inscription ON tb_class.code = tb_inscription.code AND tb_inscription.studentId =" + id;
        RowMapper<Class> rowMapper = (rs, rowNum) -> {
            String code = rs.getString("code");
            String title = rs.getString("classTitle");
            String description = rs.getString("classDescription");
            return new Class(code,title,description);
        };
        return jdbcTemplate.query(sql,rowMapper);
    }


}
