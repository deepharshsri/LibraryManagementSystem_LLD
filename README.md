# Library Management System LLD

**Functional Requirement :**

Library Management: 

1. Library Management will manage book status.
2. Library Management will have User Management and Book Management.
3. It will include a function to check the book's return time and who borrowed it using the book Id.
4. It will allow members to borrow books from the library.
5. The library will provide the book ID by book name using book management.
6. Non-members can read only inside the library.

Book Management

1. Books have an ID, a name.
2. Book Management will have a map of book id and quantity.
3. Book availability is based on the quantity available in the library, whether it is issued or not.
4. Book Status is of Enum type 
    1. Available: Book is available to borrow.
    2.  Not_Available: Book is not available at the library.
    3.  availableLater: The library has the book, but it is borrowed by someone this time.

User Management

1. A user can become a member of the library to borrow books.
2. A user can be added or removed.
3. A user will have Id, a name, a mobile number, and a list of books that he borrowed from the library, and a boolean indicating whether he is a member.
4. A user can borrow and return a book to the library; this is handled here.

**Out Of Scope:**

1. Notification sent to both libraries and the borrower x days before deadline.
2. Charges apply if not returned on time.
3. Subscription categories like silver - only allow reading in the library, gold - allow taking books to home for a certain period and diamond allow reading in the library as well as allowed to take book at home for x+y days.

**Non-Functional Requirement:** 

1. Maintainability should be good enough, in future other developer easy to understand code.
2. Scalability not in the LLD, but telling just on-demand large storage easy to scale up.
3. Extensible- new feature can be added to software without touching existing one,

Core Entities: 

1. User Class - String ID(name+mobileNumber), String name,String mobile number, boolean isMember, getName(), getId(), getMobileNumber(),setMember(boolean);
2. User Management - 
    1. Map<userId,User> userList,
    2. Map<mobileNumber, userId> mobileToUserID,
    3. Map<UserID,Map<BookId, LocalDate>> borrowedBooksByUserId,
    4. createNewUser(String,String), 
    5. deleteUser(String)
    6. addBookToUserList(String, String), 
    7. removeBookFromUserList(String, String),  
    8. findUserByMobileNumber(String)
    9. findByUserID(String)
    10. updateMembership(String, boolean),
3. Book Class: String ID, String Name, getName(), getId().
4. Book Management: 
    1. Map<String,Book> books
    2. Map<String,  Integer> bookQuantity, 
    3. Map<String,String> bookNameToId , 
    4. createBook(String,String), 
    5. decreaseQuantity(String,Integer), 
    6. increaseQuantity(String,Integer)
    7. getBookByName(String)
    8. getBookById(String)
    9. getQuantity()
5. BookStatus :
    1. AVAILABLE
    2. NOT_AVAILABLE
    3. AVAILABLE_LATER
6. Library Management: 
    1. ReturnDays=7;
    2. Map<String(BookId),Map<String, LocalDate>> borrowerListByBookId,
    3. BookManagement bm, 
    4. User Management um, 
    5. getBookStatus(String),
    6. issueBook(String, String) -it will add the user and book to a Map.
    7. deleteBookFromUserRecord(String,String) —private
    8. canBorrow(userId,BookId),
    9. getListOfBorrowerbyBookId(string), 
    10. getListOfBookIssuedByUser(String) return Map,
    11. returnBook(String,String)
