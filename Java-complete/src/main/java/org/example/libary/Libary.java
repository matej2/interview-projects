package org.example.libary;

public class Libary {
    public static void run() {
        LibaryManagement libary = new LibaryManagement();
        libary.addBook("Title3", "Author3");
        libary.addBook("Title4", "Author4", "Genre4");
        libary.print();
    }
}
