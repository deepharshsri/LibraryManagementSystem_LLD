# Library Management System (LLD)
 
A small low-level design for a library: books with stock, members who borrow and return, and a librarian view of who holds a book and when it is due.
 
## Functional Requirements
 
**Library Management**
 
1. Manages book status.
2. Has a User Management and a Book Management.
3. Provides a function to check a book's return time and who borrowed it, using the book ID.
4. Allows members to borrow books from the library.
5. Gets the book ID from a book name through Book Management.
6. Non-members can read only inside the library.
**Book Management**
 
1. A book has an ID and a name.
2. Keeps a map of book ID to quantity (copies currently on the shelf).
3. Availability is based on the quantity on the shelf.
4. Book status is an enum:
   - `AVAILABLE`: the book can be borrowed.
   - `NOT_AVAILABLE`: the library does not have the book.
   - `AVAILABLE_LATER`: the library has the book, but every copy is borrowed right now.
**User Management**
 
1. A user can become a member of the library.
2. A user can be added or removed.
3. A user has an ID, a name, a mobile number, and a member flag.
4. The books a user has borrowed are tracked here, against the user ID.
5. Borrowing and returning are coordinated by Library Management and recorded here.
## Assumptions
 
- Book names are unique; user ID is `name_mobileNumber`.
- `quantity` means copies currently on the shelf: borrow decrements it, return increments it.
- A book stays in the catalog at quantity 0, which is what makes it `AVAILABLE_LATER` instead of `NOT_AVAILABLE`.
- The return period is fixed at 7 days. Only the issue date is stored; the due date is `issueDate + 7 days`.
- Only members can take books home; reading inside the library needs no stock change.
## Out Of Scope
 
1. Notifications to the librarian and the borrower x days before the deadline.
2. Charges for late returns.
3. Subscription categories: silver (read inside only), gold (take home for a certain period), diamond (read inside and take home for x+y days).
4. Borrow limit, borrowing the same book twice, deleting a user who still holds books, concurrency, search, persistence.
## Non-Functional Requirements
 
1. **Maintainability:** each class has one job, so another developer can follow the code easily.
2. **Scalability:** not part of this LLD. Storage sits behind the managers, so it can be moved to a database that scales on demand.
3. **Extensibility:** new features can be added without changing existing classes.
## Class Diagram
 
Add the diagram image here (for example `docs/class-diagram.png`).
 
## Core Entities
 
1. **User**: `id`, `name`, `mobileNumber`, `isMember`; `getId()`, `getName()`, `getMobileNumber()`, `isMember()`, `setMember(boolean)`.
2. **Book**: `id`, `name`; `getId()`, `getName()`.
3. **BookStatus** (enum): `AVAILABLE`, `NOT_AVAILABLE`, `AVAILABLE_LATER`.
4. **BookManagement**
   - Fields: `books` (id to Book), `bookQuantity` (id to count), `bookNameToId` (name to id).
   - Methods: `createBook(id, name, quantity)`, `hasBook(id)`, `getBookById(id)`, `getBookIdByName(name)`, `getQuantity(id)`, `increaseQuantity(id, count)`, `decreaseQuantity(id, count)`.
5. **UserManagement**
   - Fields: `userList` (id to User), `mobileToUserId`, `borrowedBooksByUserId` (userId to bookId to issueDate).
   - Methods: `createNewUser(name, mobile)`, `deleteUser(userId)`, `findByUserId(userId)`, `findUserByMobileNumber(mobile)`, `updateMembership(userId, boolean)`, `addBookToUserList(userId, bookId)`, `removeBookFromUserList(userId, bookId)`, `getListOfBookIssuedByUser(userId)`.
6. **LibraryManagement**
   - Fields: `RETURN_DAYS = 7`, `borrowerListByBookId` (bookId to userId to issueDate), `bm`, `um`.
   - Methods: `getBookStatus(bookId)`, `canBorrow(userId, bookId)`, `issueBook(userId, bookId)`, `returnBook(userId, bookId)`, `getListOfBorrowerByBookId(bookId)`, `getListOfBookIssuedByUser(userId)`, private `deleteBookFromUserRecord(userId, bookId)`.
## Flow
 
- **Issue:** `canBorrow` (user exists, is a member, book is `AVAILABLE`), then decrease quantity, add the book to the user's list, add the user to the book's borrower list.
- **Return:** remove the book from both records, then increase quantity.
- **Status:** not in catalog is `NOT_AVAILABLE`, quantity above 0 is `AVAILABLE`, otherwise `AVAILABLE_LATER`.
## Data Structures
 
Hash maps keyed by ID, so lookup, add and remove are O(1) on average. The librarian view for one book is O(k), where k is the number of copies currently borrowed.
 
## Run
 
Plain Java, no framework:
 
```
javac -d out src/main/java/com/Deepansh/LibraryManagementSystem/*.java
java -cp out com.Deepansh.LibraryManagementSystem.Main
