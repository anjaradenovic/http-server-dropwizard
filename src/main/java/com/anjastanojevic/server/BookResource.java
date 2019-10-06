package com.anjastanojevic.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/book")
public class BookResource {

    private List<Book> books = new ArrayList<>();

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public Response bookInfo(@QueryParam("isbn") String requestedIsbn){

        for (Book book : books) {
            if(book.getIsbn().equals(requestedIsbn)){
                return Response.ok(book).build();
            }
        }
        return Response.status(404).build();
    }

    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public Response addBook(Book book){

        for (Book book1 : books) {
            if(book1.getIsbn().equals(book.getIsbn())){
                return Response.status(400).build();
            }
        }

        books.add(book);

        System.out.println("dodata je: " +book);

        for (Book book1 : books) {
            System.out.println(book1 + "\n");
        }
        return Response.accepted().build();
    }
}
