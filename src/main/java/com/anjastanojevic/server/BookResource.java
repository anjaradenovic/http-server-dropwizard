package com.anjastanojevic.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

        String url = "jdbc:postgresql://localhost/postgres";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");

        try {
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement statement = conn.prepareStatement("insert into book (title,authors,pages,genre,isbn) values (?,?,?,?,?);");
            statement.setString(1,book.getTitle());

            String authors = String.join(",",book.getAuthors());
            statement.setString(2,authors);

            statement.setInt(3,book.getNumberOfPages());
            statement.setString(4,book.getGenre());
            statement.setString(5,book.getIsbn());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("dodata je: " +book);

        return Response.accepted().build();
    }
}
