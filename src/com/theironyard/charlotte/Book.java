package com.theironyard.charlotte;

import java.util.Scanner;

/**
 * Created by lindseyringwald on 8/10/16.
 */
class Book {
    private static String title;
    private static String author;
    private static String genre;
    private static String numOfPages;
    private static String yearWritten;

    public Book(){

    }

    private Book(String title, String author, String genre, String numOfPages, String yearWritten){
        Book.title = title;
        Book.author = author;
        Book.genre = genre;
        Book.numOfPages = numOfPages;
        Book.yearWritten = yearWritten;
    }

    @Override
    public String toString() {
        return Book.title + ", "+ Book.author +", "+ Book.genre + ", " + Book.numOfPages + "number of pages, written in, " + Book.yearWritten;
    }


    static Book createItem() {
        System.out.println("What's the title of your book?");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        System.out.println("Who is the author?");
        String author = scanner.nextLine();

        System.out.println("What is the genre?");
        String genre = scanner.nextLine();

        System.out.println("How many pages?");
        String numOfPages = scanner.nextLine();

        System.out.println("What year was it written?");
        String yearWritten = scanner.nextLine();

    return new Book(title, author, genre, numOfPages, yearWritten);
    }
}
