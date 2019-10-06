package com.anjastanojevic.client;

public class Main {
    public static void main(String[] args) {
        BookClient bookClient = new BookClient();
        Book book = bookClient.getBookByIsbn("978-86-10-02326-8");
        System.out.println("Klijent je dobio odgovor: " + book);
    }
}
