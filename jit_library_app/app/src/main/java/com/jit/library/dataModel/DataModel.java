package com.jit.library.dataModel;

/**
 * Created by Datta on 16/10/2018.
 */

public class DataModel {
    String bookId, bookName, bookAuthor,bookPub,bookBranch,bookYear;

    public DataModel(String bookId, String bookName, String bookAuthor, String bookPub, String bookBranch, String bookYear) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPub = bookPub;
        this.bookBranch = bookBranch;
        this.bookYear = bookYear;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getbookId() {
        return bookId;
    }

    public void setbookId(String userName) {
        this.bookId = bookId;
    }

    public String getbookName() {
        return bookName;
    }

    public void setbookName(String bookName) {
        this.bookName = bookName;
    }

    public String getbookAuthor() {
        return bookAuthor;
    }

    public void setbookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getbookPub() {
        return bookPub;
    }

    public void setbookPub(String bookPub) {
        this.bookPub = bookPub;
    }

    public String getbookBranch() {
        return bookBranch;
    }

    public void setbookBranch(String bookBranch) {
        this.bookBranch = bookBranch;
    }

    public String getbookYear() {
        return bookYear;
    }

    public void setbookYear(String bookYear) {
        this.bookYear = bookYear;
    }
}
