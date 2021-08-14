package com.gary.schoology.interfaces;

import java.util.List;

public interface SearchInterface {
    public List list_students(String q);
    public List list_classes(String q);
    public List list_inscriptions(String q);
}
