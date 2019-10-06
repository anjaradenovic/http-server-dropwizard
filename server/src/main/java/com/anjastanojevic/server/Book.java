package com.anjastanojevic.server;

import java.util.List;

public class Book {

    private String title;
    private List<String> authors;
    private int numberOfPages;
    private String genre;
    private String isbn;

    public Book(String title, List<String> authors, int numberOfPages, String genre, String isbn) {
        this.title = title;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Book(){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", numberOfPages=" + numberOfPages +
                ", genre='" + genre + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
