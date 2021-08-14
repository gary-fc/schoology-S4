package com.gary.schoology.model;

public class Graphic {
    int students;
    int classes;
    int inscriptions;

    public Graphic() {
    }

    public Graphic(int students, int classes, int inscriptions) {
        this.students = students;
        this.classes = classes;
        this.inscriptions = inscriptions;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(int inscriptions) {
        this.inscriptions = inscriptions;
    }
}
