package com.gary.schoology.dao;

import com.gary.schoology.interfaces.InscriptionInterface;
import com.gary.schoology.model.Inscription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class InscriptionDAO implements InscriptionInterface {

    private JdbcTemplate jdbcTemplate;

    public InscriptionDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List list() {
        String sql = "SELECT * FROM tb_inscription";
        RowMapper<Inscription> rowMapper = (rs, rowNum) -> {
            Integer id_inscription = rs.getInt("id_inscription");
            Integer studentId = rs.getInt("studentId");
            String code = rs.getString("code");
            return new Inscription(id_inscription,studentId,code);

        };

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public int add(int studentId, String code) {
        String sql = "INSERT INTO tb_inscription(studentId,code) VALUES (?,?)";
        return jdbcTemplate.update(sql,studentId,code);
    }

    @Override
    public int delete(int id_inscription) {
        String sql = "DELETE FROM tb_inscription WHERE id_inscription = ?";
        return jdbcTemplate.update(sql,id_inscription);
    }
}
