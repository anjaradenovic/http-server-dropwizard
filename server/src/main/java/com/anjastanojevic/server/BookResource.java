package com.anjastanojevic.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Path("/book")
public class BookResource {

    private String databaseUrl;
    private Properties props;

    public BookResource(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response bookInfo(@QueryParam("isbn") String requestedIsbn) {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = DriverManager.getConnection(databaseUrl, props);
            statement = conn.prepareStatement
                    ("select * from book where isbn = ?;");
            statement.setString(1, requestedIsbn);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");

                String authorsAsString = resultSet.getString("authors");
                List<String> authors = Arrays.asList(authorsAsString.split(","));

                int numberOfPages = resultSet.getInt("pages");

                String genre = resultSet.getString("genre");

                String isbn = resultSet.getString("isbn");

                Book book = new Book(title, authors, numberOfPages, genre, isbn);

                return Response.ok(book).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(databaseUrl, props);
            statement = conn.prepareStatement
                    ("insert into book (title,authors,pages,genre,isbn) values (?,?,?,?,?);");
            statement.setString(1, book.getTitle());

            String authors = String.join(",", book.getAuthors());
            statement.setString(2, authors);

            statement.setInt(3, book.getNumberOfPages());
            statement.setString(4, book.getGenre());
            statement.setString(5, book.getIsbn());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Response.accepted().build();
    }
}
