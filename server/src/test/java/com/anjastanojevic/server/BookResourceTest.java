package com.anjastanojevic.server;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class BookResourceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(Application.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    @Test
    public void testGetExistingBook() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client 1");

        Book book = client.target(
                "http://localhost:8080/book?isbn=" + "978-86-10-02326-8")
                .request()
                .get(Book.class);

        assertEquals(book.getGenre(), "horror");
        assertEquals(book.getNumberOfPages(), 250);
        assertEquals(book.getTitle(), "Green mile");
        assertThat(book.getAuthors()).containsExactly("Steven King");
    }

    @Test(expected = NotFoundException.class)
    public void testGetNonExistingBook() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client 2");

        client.target(
                "http://localhost:8080/book?isbn=" + "non-existing-isbn")
                .request()
                .get(Book.class);
    }

    @Test
    public void testAddANewBook(){

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client 3");

        int randomNumberOfPages = new Random().nextInt(500);
        // ISBN must be unique, so a random string (UUID) is used
        String randomISBN = UUID.randomUUID().toString();

        // create a book
        Book book = new Book(
                "Some book",
                Collections.singletonList("Steven King"),
                randomNumberOfPages,
                "thriller",
                randomISBN);

        // add the book
        Response response = client
                .target("http://localhost:8080/book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));

        assertEquals(202, response.getStatus());
    }

    @Test
    public void testAddDuplicateBook(){

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client 4");

        int randomNumberOfPages = new Random().nextInt(500);
        // ISBN must be unique, so a random string (UUID) is used
        String randomISBN = UUID.randomUUID().toString();

        // create a book
        Book book = new Book(
                "Some book",
                Collections.singletonList("Steven King"),
                randomNumberOfPages,
                "thriller",
                randomISBN);

        // add the book
        Response response = client
                .target("http://localhost:8080/book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));

        // add the same book again
        Response response2 = ClientBuilder
                .newClient()
                .target("http://localhost:8080/book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));

        // response code should be 400 which means "BAD_REQUEST"
        assertEquals(400, response2.getStatus());
    }
}