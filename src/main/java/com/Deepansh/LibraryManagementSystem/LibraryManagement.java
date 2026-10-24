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
        return bm.books.containsKey(bookId)?(bm.getBookQuantity(bookId)>0?BookStatus.AVAILABLE:BookStatus.AVAILABLE_LATER):
BookStatus.NOT_AVAILABLE;
    }

    public boolean canBorrow(String userId,String bookId){ 
       BookStatus status = getBookStatus(bookId);
       if(status==BookStatus.NOT_AVAILABLE) {
        System.out.println("Book is not available in our library");
        return false;
       }
       if(status==BookStatus.AVAILABLE_LATER) {
        System.out.println("Book will be available later after : "+ borrowerListByBookId.get(bookId).get(userId).plusDays(returnDate));
        return false;
       }        

    return true;
    }

    public void issueBook(String userId, String bookId){
        um.addBookToUserList(bookId, userId);
        bm.decreaseQuantity(bookId, 1);
       borrowerListByBookId.computeIfAbsent(bookId,k->new HashMap<>()).put(userId,LocalDate.now());
       
    }

    public void returnBook(String userId, String bookId){
       um.removeBookFromUser(bookId, userId);
       bm.increaseQuantity(bookId, 1);
       borrowerListByBookId.get(bookId).remove(userId);
    }

}
