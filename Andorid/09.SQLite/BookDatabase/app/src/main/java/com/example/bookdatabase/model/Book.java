package com.example.bookdatabase.model;

/**
 * Created by SUNGWOO on 2017-02-07.
 */

public class Book {

    private int id;
    private String title;
    private String author;
    private String press;

    public Book(){}

    public Book(String title, String author, String press) {
        super();
        this.title = title;
        this.author = author;
        this.press = press;
    }

    //getters & setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPress() {
        return this.press;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author
                + ", press=" + press + "]";
    }
}