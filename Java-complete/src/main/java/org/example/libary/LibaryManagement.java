package org.example.libary;

import java.util.ArrayList;

public class LibaryManagement {
    // For demonstration purposes
    private static int booksAdded =  0;

    private final ArrayList<Book> books = new ArrayList<>();

    public LibaryManagement() {
        books.add(new Book("Title", "Author", "Genre"));
        books.add(new Book("Title2", "Author2", "Genre2"));
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author, null));
        booksAdded++;
    }

    public void addBook(String title, String author, String genre) {
        books.add(new Book(title, author, genre));
        booksAdded++;
    }

    public void print() {
        books.forEach(System.out::println);
        System.out.println(booksAdded);
    }
}
