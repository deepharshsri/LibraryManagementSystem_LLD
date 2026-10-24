package com.Deepansh.LibraryManagementSystem;

import java.util.*;
import java.time.LocalDate;

public class UserManagement {


    public Map<String, User> users = new HashMap<>();
    public Map<String,Map<String,LocalDate>> borrowedBooksbyUser = new HashMap<>();

    public User createUser(String name, String mobileNumber) {
        User user = new User(name, mobileNumber);
        users.put(user.getId(), user);
        return user;
    }

    public void addBookToUserList(String bookId,String userId){
        borrowedBooksbyUser.computeIfAbsent(userId,k-> new HashMap<>()).put(bookId,LocalDate.now());
    }

    public void removeBookFromUser(String bookId,String userId){
        borrowedBooksbyUser.get(userId).remove(bookId);
    }

    public void updateMemberShip(User user,boolean flag){
        user.setMember(flag);
    }
}
