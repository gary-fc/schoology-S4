package com.gary.schoology.interfaces;


import com.gary.schoology.model.Student;

import java.util.List;

public interface StudentInterface {

    public List list();
    public Student get(int id);
    public int add(Student student);
    public int edit(Student student);
    public int delete(Student student);
    public List listStudentClass(String id);

}
