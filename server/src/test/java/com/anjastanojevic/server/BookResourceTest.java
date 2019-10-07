package com.anjastanojevic.server;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class BookResourceTest {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<>(Application.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    @Test
    public void testGetExistingBook() {

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

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

        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

        client.target(
                "http://localhost:8080/book?isbn=" + "non-existing-isbn")
                .request()
                .get(Book.class);
    }
}