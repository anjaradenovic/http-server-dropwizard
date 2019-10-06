package com.anjastanojevic.client;

import com.anjastanojevic.client.Book;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
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
}
