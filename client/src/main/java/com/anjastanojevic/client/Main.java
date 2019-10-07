package com.anjastanojevic.client;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        BookClient bookClient = new BookClient();

        List<String> isbnNumbersOfAddedBooks = new ArrayList<>();

        // add some books
        for (int i = 0; i < 10; i++) {
            // add a book
            int randomNumberOfPages = new Random().nextInt(500);
            // ISBN must be unique, so a random string (UUID) is used
            String randomISBN = UUID.randomUUID().toString();

            Book book = new Book(
                    "Some book",
                    Collections.singletonList("Steven King"),
                    randomNumberOfPages,
                    "thriller",
                    randomISBN);

            bookClient.addBook(book);
            System.out.println("Added book: " + book);
            isbnNumbersOfAddedBooks.add(randomISBN);
        }

        // fetch some of them by isbn
        for (int i = 0; i < 3; i++) {
            Book book = bookClient.getBookByIsbn(isbnNumbersOfAddedBooks.get(i));
            System.out.println("Fetched:" + book);
        }
    }
}
