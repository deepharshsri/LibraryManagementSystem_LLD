package com.Deepansh.LibraryManagementSystem;

import java.util.*;

public class BookManagement {


 public Map<String, Book> books =new HashMap<>();
 public Map<String, Integer> bookQuantity = new HashMap<>();
 public Map<String,String> bookNameToId = new HashMap<>();


 public Book createBook(String name, String id, int quantity) {
        Book book = new Book(name, id);
        books.put(id, book);
        bookQuantity.put(id, quantity);
        bookNameToId.put(name, id);
        return book;
    }
    public int getBookQuantity(String id) {
        return bookQuantity.get(id);
    }

    public void increaseQuantity(String id, int quantity) {
      bookQuantity.put(id,bookQuantity.get(id) + quantity);
    }

    public void decreaseQuantity(String id, int quantity) {
        bookQuantity.put(id,bookQuantity.get(id) - quantity);
    }

}
