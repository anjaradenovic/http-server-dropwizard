package com.anjastanojevic.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Path("/book")
public class BookResource {

    private List<Book> books = new ArrayList<>();

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public Book bookInfo(){

        List<String> authors = new ArrayList<>();
        authors.add("Stiven King");
        Book book = new Book("Green mile",authors,326, "horror","978-86-10-02326-8");

        return book;
    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public void addBook(Book book){

        books.add(book);

        System.out.println("dodata je: " +book);

        for (Book book1 : books) {
            System.out.println(book1 + "\n");
        }
    }
}
