package com.gary.schoology.dao;

import com.gary.schoology.interfaces.StudentInterface;
import com.gary.schoology.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class StudentDAO implements StudentInterface {
    private JdbcTemplate jdbcTemplate;

    public StudentDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List list() {
        String sql = "SELECT * FROM tb_student";
        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            Integer studentId = rs.getInt("studentId");
            String lastName = rs.getString("lastName");
            String firstName = rs.getString("firstName");
            return new Student(studentId,lastName,firstName);

        };

        return jdbcTemplate.query(sql,rowMapper);

    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public int add(Student student) {
        String sql = "INSERT INTO tb_student(lastName,firstName) VALUES (?,?)";
        return jdbcTemplate.update(sql,student.getLastName(),student.getFirstName());
    }

    @Override
    public int edit(Student student) {
        String sql = "UPDATE tb_student SET lastName = ?, firstName = ? WHERE studentId = ?";
        return jdbcTemplate.update(sql,student.getLastName(),student.getFirstName(),student.getStudentId());
    }

    @Override
    public int delete(Student student) {
        String sql = "DELETE FROM tb_student WHERE studentId = ?";
        return jdbcTemplate.update(sql,student.getStudentId());
    }

    @Override
    public List listStudentClass(String id) {
        String sql = "SELECT * FROM tb_student JOIN tb_inscription ON tb_student.studentId = tb_inscription.studentId AND tb_inscription.code = " +"'"+id+"'" ;
        RowMapper<Student> rowMapper = (rs, rowNum) -> {
            Integer studentId = rs.getInt("studentId");
            String lastName = rs.getString("lastName");
            String firstName = rs.getString("firstName");
            return new Student(studentId,lastName,firstName);
        };
        return jdbcTemplate.query(sql,rowMapper);
    }
}
