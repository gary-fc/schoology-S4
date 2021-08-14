package com.gary.schoology.interfaces;

import com.gary.schoology.model.Class;


import java.util.List;

public interface ClassInterface {
    public List list();
    public Class get(int id);
    public int add(Class classes);
    public int edit(Class classes);
    public int delete(Class classes);
    public List listClassStudent(int id);
}
