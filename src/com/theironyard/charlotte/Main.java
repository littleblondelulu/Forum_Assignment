package com.theironyard.charlotte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jdk.nashorn.internal.parser.JSONParser;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;
import static java.lang.System.out;


public class Main {
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Book.saveBook();
        //When the program first boots, read the file and display the contents.
        Book.nextLine();
        Book.loadBook();
        System.out.println("Please add a new book to the book collection!");
        addNewBook();
        Book.updateBook();

    }

    //Get input values to populate a new book Object
    private static Book addNewBook() {
        out.println("What's the title of your book?");

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        out.println("Who is the author?");
        String author = scanner.nextLine();

        out.println("What is the genre?");
        String genre = scanner.nextLine();

        out.println("How many pages?");
        String numOfPages = scanner.nextLine();

        out.println("What year was it written?");
        String yearWritten = scanner.nextLine();

        Book book = new Book(title, author, genre, numOfPages, yearWritten);
        books.add(book);
        return book;
    }


    }
