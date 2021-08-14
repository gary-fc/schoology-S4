package com.gary.schoology.model;

public class Class {
    String code;
    String classTitle;
    String classDescription;

    public Class() {
    }

    public Class(String code, String classTitle, String classDescription) {
        this.code = code;
        this.classTitle = classTitle;
        this.classDescription = classDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
