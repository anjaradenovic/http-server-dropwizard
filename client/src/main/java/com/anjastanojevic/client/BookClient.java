package com.anjastanojevic.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class BookClient {

    private Client client;

    public BookClient() {
        client = ClientBuilder.newClient();
    }

    public Book getBookByIsbn(String isbn){

        Book book = client
                .target("http://localhost:8080/book?isbn=" + isbn)
                .request(MediaType.APPLICATION_JSON)
                .get(Book.class);

        return book;
    }

    public void addBook(Book book) {

        client
            .target("http://localhost:8080/book")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(book, MediaType.APPLICATION_JSON));
    }
}
