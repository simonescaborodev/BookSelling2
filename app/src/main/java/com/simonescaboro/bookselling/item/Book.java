package com.simonescaboro.bookselling.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Simone on 10/07/2017.
 */

public class Book{
    public String getBookId() {
        return bookId;
    }

    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookEditor;
    private String bookDescription;
    private String bookLanguage;
    private String bookSeller;
    private String bookPrice;
    private String bookISBN;

    public String getBookQuality() {
        return bookQuality;
    }

    private String bookQuality;
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

    public Book(String bookId, String bookTitle, String bookAuthor, String bookEditor, String bookDescription, String bookLanguage, String bookSeller, String bookPrice, String bookISBN, int bookEdition, int bookPublishingYear, String bookQuality) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookEditor = bookEditor;
        this.bookDescription = bookDescription;
        this.bookLanguage = bookLanguage;
        this.bookSeller = bookSeller;
        this.bookPrice = bookPrice;
        this.bookISBN = bookISBN;
        this.bookEdition = bookEdition;
        this.bookPublishingYear = bookPublishingYear;
        this.bookQuality = bookQuality;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookEditor() {
        return bookEditor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getBookSeller() {
        return bookSeller;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public int getBookEdition() {
        return bookEdition;
    }

    public int getBookPublishingYear() {
        return bookPublishingYear;
    }

}