package com.gary.schoology.interfaces;

import com.gary.schoology.model.Inscription;
import com.gary.schoology.model.Student;

import java.util.List;

public interface InscriptionInterface {
    public List list();
    public int add(int studentId, String code);
    public int delete(int id_inscription);


}
