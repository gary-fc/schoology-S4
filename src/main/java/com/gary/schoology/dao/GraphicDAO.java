package com.gary.schoology.dao;

import com.gary.schoology.interfaces.GraphicInterface;
import com.gary.schoology.model.Graphic;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraphicDAO implements GraphicInterface {

    private JdbcTemplate jdbcTemplate;

    public GraphicDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Graphic get_data() {
        String sql = "SELECT  (SELECT COUNT(*) FROM tb_student) AS students, (SELECT COUNT(*) FROM tb_class) AS classes,(SELECT COUNT(*) FROM tb_inscription) AS inscriptions FROM dual";
        ResultSetExtractor<Graphic> extractor = new ResultSetExtractor<Graphic>() {
            @Override
            public Graphic extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()){
                    Integer students = rs.getInt("students");
                    Integer classes = rs.getInt("classes");
                    Integer inscriptions = rs.getInt("inscriptions");
                    return new Graphic(students,classes,inscriptions);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql,extractor);
    }
}
