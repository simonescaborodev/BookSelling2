package com.simonescaboro.bookselling.item;

/**
 * Created by Simone on 10/07/2017.
 */

public class Book {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookHouse;
    private String bookDescription;
    private String bookLanguage;
    private String bookSeller;
    private int bookEdition;
    private int bookPublishingYear;

    public Book(String bookTitle, String bookAuthor, String bookSeller, String bookId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookSeller = bookSeller;
        this.bookId = bookId;
    }
    public Book(){

    }
    public Book(String bookTitle, String bookAuthor, String bookHouse, String bookDescription, String bookLanguage, int bookEdition, int bookPublishingYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookHouse = bookHouse;
        this.bookDescription = bookDescription;
        this.bookLanguage = bookLanguage;
        this.bookEdition = bookEdition;
        this.bookPublishingYear = bookPublishingYear;
    }

    public String getBookSeller() {
        return bookSeller;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookHouse() {
        return bookHouse;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public int getBookEdition() {
        return bookEdition;
    }

    public int getBookPublishingYear() {
        return bookPublishingYear;
    }
}