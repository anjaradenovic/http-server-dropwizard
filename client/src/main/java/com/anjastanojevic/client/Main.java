package com.anjastanojevic.client;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        BookClient bookClient = new BookClient();

        // fetch a book by isbn
        Book book = bookClient.getBookByIsbn("978-86-10-02326-8");
        System.out.println("Klijent je dobio odgovor: " + book);

        // add a book
        int randomNumberOfPages = new Random().nextInt(500);
        // ISBN must be unique, so we use a random string (UUID)
        String randomISBN = UUID.randomUUID().toString();

        Book newBook = new Book(
                "Some book",
                Collections.singletonList("Steven King"),
                randomNumberOfPages,
                "thriller",
                randomISBN);

        bookClient.addBook(newBook);
    }
}
