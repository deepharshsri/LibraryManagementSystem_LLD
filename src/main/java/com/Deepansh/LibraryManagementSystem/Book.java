package com.Deepansh.LibraryManagementSystem;

public class Book {


    private String id;
    private String name;

    public Book(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public String getId() {
        return id;
    }
}
