package com.Deepansh.LibraryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		

		UserManagement userManagement = new UserManagement();
		User u1=userManagement.createUser("Deepansh","1234567890");
		BookManagement bookManagement = new BookManagement();
		Book b1=bookManagement.createBook("Java","1",1);
		LibraryManagement libraryManagement = new LibraryManagement(bookManagement,userManagement);

		boolean res=libraryManagement.canBorrow(u1.getId(),b1.getId());

		if (res) libraryManagement.issueBook(u1.getId(),b1.getId());
		else System.out.println("Cannot issue book");

		
	}

}
