package com.gary.schoology.model;

public class Inscription {
    int  id_inscription;
    int  studentId;
    String code;

    public Inscription() {
    }

    public Inscription(int id_inscription, int studentId, String code) {
        this.id_inscription = id_inscription;
        this.studentId = studentId;
        this.code = code;
    }

    public int getId_inscription() {
        return id_inscription;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
