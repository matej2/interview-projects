package org.example.libary;

public class Book {
    private String title;
    private String author;
    private String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String toString() {
        return String.format("%s by %s, genre: %s", title, author, genre);
    }
}
