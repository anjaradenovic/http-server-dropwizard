package com.anjastanojevic.client;

import com.anjastanojevic.client.Book;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

public class BookClient {

    public Book getBookByIsbn(String isbn){

        Book book = ClientBuilder
                .newClient()
                .target("http://localhost:8080/book?isbn=" + isbn)
                .request(MediaType.APPLICATION_JSON)
                .get(Book.class);

        return book;
    }

    public void addBook(Book book){

        ClientBuilder
                .newClient()
                .target("http://localhost:8080/book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
    }
}
