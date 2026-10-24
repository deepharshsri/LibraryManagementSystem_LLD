package com.Deepansh.LibraryManagementSystem;
import java.util.*;
import java.time.LocalDate;

public class LibraryManagement {


    private final int returnDate=7;

    Map<String,Map<String,LocalDate>> borrowerListByBookId =new  HashMap<>();
    BookManagement bm;
    UserManagement um;
    

    public LibraryManagement(BookManagement bm, UserManagement um) {
        this.bm = bm;
        this.um = um;
    }

    public BookStatus getBookStatus(String bookId){
        return bm.getBookQuantity(bookId)>0?BookStatus.AVAILABLE:BookStatus.NOT_AVAILABLE;
    }

    public boolean canBorrow(String userId,String bookId){ 

       Map<String,LocalDate> borrowedBooks = um.borrowedBooksbyUser.containsKey(userId) ? um.borrowedBooksbyUser.get(userId):new HashMap<>();
       if(getBookStatus(bookId)==BookStatus.NOT_AVAILABLE||borrowedBooks.containsKey(bookId)){
        return false;
    }

    return true;
    }

    public void issueBook(String userId, String bookId){
        um.addBookToUserList(bookId, userId);
        bm.decreaseQuantity(bookId, 1);
        Map<String,LocalDate> list=borrowerListByBookId.containsKey(bookId)? borrowerListByBookId.get(userId):new HashMap<>();
        list.put(userId,LocalDate.now());
        borrowerListByBookId.put(bookId,list);
    }

    public void returnBook(String userId, String bookId){
       um.removeBookFromUser(bookId, userId);
       bm.increaseQuantity(bookId, 1);
       borrowerListByBookId.get(bookId).remove(userId);
    }

}
